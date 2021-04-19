package org.five.nav.services.implementation;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.five.nav.domain.User;
import org.five.nav.domain.enums.UserStatus;
import org.five.nav.repository.UserRepository;
import org.five.nav.services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@ApplicationScoped
@Slf4j
public class UserServiceV1 implements UserService {

    @Inject
    UserRepository userRepository;

    @Override
    @Transactional
    public User registerUser(OidcJwtCallerPrincipal principal) {
        Optional<User> user = userRepository.findByEmail(principal.getName());
        if(user.isPresent()){
            log.info("User with email: {} already in database!", principal.getName());
            return user.get();
        }else {
            User created = User.builder()
                    .name(principal.getClaim("name"))
                    .lastname(principal.getClaim("family_name"))
                    .email(principal.getName())
                    .role("AUTHOR")
                    .status(UserStatus.ACTIVE)
                    .articles(new ArrayList<>())
                    .likedArticles(new ArrayList<>())
                    .build();
            userRepository.persistAndFlush(created);
            log.info("User successfully saved to database with id: {}", created.getId());
            return created;
        }
    }
}

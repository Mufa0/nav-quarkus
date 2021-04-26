package org.five.nav.services.implementation;

import lombok.extern.slf4j.Slf4j;
import org.five.nav.client.user.AuthUser;
import org.five.nav.domain.User;
import org.five.nav.repository.UserRepository;
import org.five.nav.services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;

@ApplicationScoped
@Slf4j
public class UserServiceV1 implements UserService {

    @Inject
    UserRepository userRepository;

    @Override
    public User registerUser(AuthUser authUser) {
        Optional<User> user = userRepository.findByEmail(authUser.getEmail());
        if(user.isPresent()){
            log.info("User with email: {} already in database!", authUser.getName());
            return user.get();
        }else {
            User created = User.builder()
                    .code(authUser.getCode())
                    .email(authUser.getEmail())
                    .articles(new ArrayList<>())
                    .likedArticles(new ArrayList<>())
                    .build();
            userRepository.persistAndFlush(created);
            log.info("User successfully saved to database with id: {}", created.getId());
            return created;
        }
    }
}

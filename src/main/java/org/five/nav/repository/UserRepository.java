package org.five.nav.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.five.nav.domain.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Optional<User> findByEmail(String email){
        return find("email",email).firstResultOptional();
    }
}

package org.five.nav.services;

import org.five.nav.client.user.AuthUser;
import org.five.nav.domain.User;


public interface UserService {

    User registerUser(AuthUser authUser);

}

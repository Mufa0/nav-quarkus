package org.five.nav.services;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import org.five.nav.domain.User;

import java.security.Principal;

public interface UserService {

    User registerUser(Principal principal);
}

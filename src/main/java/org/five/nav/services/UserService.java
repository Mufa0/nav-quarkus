package org.five.nav.services;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import org.five.nav.domain.User;

public interface UserService {

    User registerUser(OidcJwtCallerPrincipal principal);
}

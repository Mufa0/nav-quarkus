package org.five.nav.security;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.five.nav.services.UserService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Slf4j
public class SecurityFilter implements ContainerRequestFilter {
    @Inject
    UserService userService;

    @Override
    @Transactional
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        OidcJwtCallerPrincipal principal = (OidcJwtCallerPrincipal) containerRequestContext.getSecurityContext().getUserPrincipal();
        if(principal != null){
            userService.registerUser(principal);
        }
    }
}

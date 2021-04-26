package org.five.nav.security;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.five.nav.client.user.AuthUser;
import org.five.nav.client.user.AuthUserController;
import org.five.nav.client.user.AuthUserRequest;
import org.five.nav.services.UserService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
@Slf4j
public class SecurityFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Inject
    @RestClient
    AuthUserController authUserController;

    @Override
    @Transactional
    public void filter(ContainerRequestContext containerRequestContext) {
        OidcJwtCallerPrincipal principal = (OidcJwtCallerPrincipal) containerRequestContext.getSecurityContext().getUserPrincipal();
        if(principal != null){
            AuthUserRequest request = AuthUserRequest.builder()
                    .email(principal.getName())
                    .lastname(principal.getClaim("family_name"))
                    .name(principal.getClaim("name"))
                    .roles(List.of("author"))
                    .build();
            AuthUser user = authUserController.register(request);
            userService.registerUser(user);
        }
    }
}

package org.five.nav.security;

import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.arc.profile.UnlessBuildProfile;
import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.runtime.QuarkusPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.auth.BasicUserPrincipal;
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
import java.io.IOException;
import java.util.List;

@Provider
@Slf4j
@IfBuildProfile("test")
public class TestSecurityFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Inject
    @RestClient
    AuthUserController authUserController;
    @Override
    @Transactional
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        QuarkusPrincipal principal = (QuarkusPrincipal) containerRequestContext.getSecurityContext().getUserPrincipal();
        if(principal != null){
            AuthUserRequest request = AuthUserRequest.builder()
                    .name(principal.getName())
                    .roles(List.of("author"))
                    .lastname("Testing")
                    .email(principal.getName())
                    .build();
            AuthUser user = authUserController.register(request);
            userService.registerUser(user);
        }
    }
}

package org.five.nav.security;

import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.arc.profile.UnlessBuildProfile;
import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.runtime.QuarkusPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.auth.BasicUserPrincipal;
import org.five.nav.services.UserService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Slf4j
@IfBuildProfile("test")
public class TestSecurityFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Override
    @Transactional
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        QuarkusPrincipal principal = (QuarkusPrincipal) containerRequestContext.getSecurityContext().getUserPrincipal();
        if(principal != null){
            userService.registerUser(principal);
        }
    }
}

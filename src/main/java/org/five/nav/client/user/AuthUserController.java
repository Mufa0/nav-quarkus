package org.five.nav.client.user;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/api/auth")
@RegisterRestClient
public interface AuthUserController {

    @POST
    AuthUser register(AuthUserRequest request);

    @GET
    @Path("/{code}")
    AuthUser get(@PathParam("code") String code);
}
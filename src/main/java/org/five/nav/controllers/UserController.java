package org.five.nav.controllers;

import org.five.nav.dto.requests.UserRequest;
import org.five.nav.dto.responses.UserResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/users")
public interface UserController {

    @POST
    @Path("/register")
    UserResponse register(UserRequest request);

    @POST
    @Path("/login")
    UserResponse login(UserRequest loginRequest);



}

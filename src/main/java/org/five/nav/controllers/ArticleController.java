package org.five.nav.controllers;

import io.quarkus.security.Authenticated;
import org.five.nav.dto.requests.ArticleRequest;
import org.five.nav.dto.responses.ArticleResponse;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
public interface ArticleController {

    @POST
    ArticleResponse createArticle(ArticleRequest articleRequest, @Context SecurityContext security);

    @GET
    List<ArticleResponse> getArticles();

    @GET
    @Path("/user")
    List<ArticleResponse> getArticlesForUser(@Context SecurityContext security);

    @GET
    @Path("/{id}")
    ArticleResponse getArticle(@PathParam("id") long id);

    @PUT
    @Path("/{id}")
    ArticleResponse updateArticle(@PathParam("id") long id, ArticleRequest request, @Context SecurityContext security);

    @DELETE
    @Path("/{id}")
    Response deleteArticle(@PathParam("id") long id, @Context SecurityContext security);

}

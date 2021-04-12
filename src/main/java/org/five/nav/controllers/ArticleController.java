package org.five.nav.controllers;

import org.five.nav.dto.requests.ArticleRequest;
import org.five.nav.dto.responses.ArticleResponse;
import org.jboss.resteasy.annotations.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/articles")
public interface ArticleController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ArticleResponse createArticle(ArticleRequest articleRequest);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ArticleResponse> getArticles();

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response deleteArticle(@PathParam("id") long id);

}

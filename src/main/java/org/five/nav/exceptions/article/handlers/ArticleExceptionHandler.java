package org.five.nav.exceptions.article.handlers;

import org.five.nav.exceptions.article.ArticleNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ArticleExceptionHandler implements ExceptionMapper<ArticleNotFoundException> {

    @Override
    public Response toResponse(ArticleNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}

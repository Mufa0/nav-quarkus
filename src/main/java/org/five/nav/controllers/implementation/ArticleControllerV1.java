package org.five.nav.controllers.implementation;

import org.five.nav.controllers.ArticleController;
import org.five.nav.dto.requests.ArticleRequest;
import org.five.nav.dto.responses.ArticleResponse;
import org.five.nav.services.ArticleService;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ArticleControllerV1 implements ArticleController {

    List<ArticleResponse> articles;

    ArticleService articleService;

    public ArticleControllerV1(ArticleService articleService){
        articles = new ArrayList<>();
        this.articleService = articleService;
    }


    @Override
    public ArticleResponse createArticle(ArticleRequest articleRequest, SecurityContext security) {
        return articleService.create(articleRequest, security);
    }

    @Override
    public List<ArticleResponse> getArticles() {

        return articleService.get();
    }

    @Override
    public List<ArticleResponse> getArticlesForUser(SecurityContext security) {

        return articleService.get(security);
    }

    @Override
    public ArticleResponse getArticle(long id) {

        return articleService.get(id);
    }

    @Override
    public ArticleResponse updateArticle(long id, ArticleRequest request, SecurityContext security) {
        return articleService.update(id, request, security);
    }

    @Override
    public Response deleteArticle(long id, SecurityContext security) {

        return articleService.delete(id, security);
    }
}

package org.five.nav.controllers.implementation;

import org.five.nav.controllers.ArticleController;
import org.five.nav.dto.requests.ArticleRequest;
import org.five.nav.dto.responses.ArticleResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ArticleControllerV1 implements ArticleController {

    List<ArticleResponse> articles;

    public ArticleControllerV1(){
        articles = new ArrayList<>();
    }

    @Override
    public ArticleResponse createArticle(ArticleRequest articleRequest) {
        ArticleResponse created = ArticleResponse.builder()
                .id(this.articles.size())
                .content(articleRequest.getContent())
                .title(articleRequest.getTitle())
                .build();

        articles.add(created);

        return created;
    }

    @Override
    public List<ArticleResponse> getArticles() {
        return this.articles;
    }

    @Override
    public Response deleteArticle(long id) {
        Optional<ArticleResponse> article = articles.stream().filter(x -> x.getId() == id).findFirst();
        if(article.isPresent()){
            articles.remove(article.get());
            return Response.ok().build();
        }else{
            return Response.serverError().status(Response.Status.NOT_FOUND).build();
        }
    }
}

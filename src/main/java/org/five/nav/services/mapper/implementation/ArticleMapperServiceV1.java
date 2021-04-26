package org.five.nav.services.mapper.implementation;

import org.five.nav.domain.Article;
import org.five.nav.domain.User;
import org.five.nav.domain.mapper.ArticleMapper;
import org.five.nav.dto.requests.ArticleRequest;
import org.five.nav.dto.responses.ArticleResponse;
import org.five.nav.dto.responses.UserResponse;
import org.five.nav.services.mapper.ArticleMapperService;
import org.five.nav.services.mapper.UserMapperService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticleMapperServiceV1 implements ArticleMapperService {

    ArticleMapper articleMapper;

    UserMapperService userMapperService;

    public ArticleMapperServiceV1(UserMapperService userMapperService){

        this.articleMapper = new ArticleMapper();
        this.userMapperService = userMapperService;
    }


    @Override
    public ArticleResponse transform(Article article) {
        UserResponse userResponse = userMapperService.transform(article.getAuthor());
        return articleMapper.from(article, userResponse);
    }

    @Override
    public Article transform(ArticleRequest request, User author) {
        Article article = articleMapper.from(request);
        article.setAuthor(author);
        return article;
    }

    @Override
    public Article transform(ArticleRequest request, Article article) {
        Article updated = articleMapper.from(request);
        article.setContent(updated.getContent());
        article.setStatus(updated.getStatus());
        article.setTitle(updated.getTitle());
        return article;
    }
}

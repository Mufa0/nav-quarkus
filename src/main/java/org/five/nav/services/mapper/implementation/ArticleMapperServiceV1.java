package org.five.nav.services.mapper.implementation;

import org.five.nav.domain.Article;
import org.five.nav.domain.mapper.ArticleMapper;
import org.five.nav.dto.requests.ArticleRequest;
import org.five.nav.dto.responses.ArticleResponse;
import org.five.nav.services.mapper.ArticleMapperService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticleMapperServiceV1 implements ArticleMapperService {

    ArticleMapper articleMapper;

    public ArticleMapperServiceV1(){
        this.articleMapper = new ArticleMapper();
    }


    @Override
    public ArticleResponse transform(Article article) {
        return articleMapper.from(article);
    }

    @Override
    public Article transform(ArticleRequest request) {
        return articleMapper.from(request);
    }

    @Override
    public Article transform(ArticleRequest request, Article article) {
        Article updated = articleMapper.from(request);
        updated.setId(article.getId());
        return updated;
    }
}

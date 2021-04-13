package org.five.nav.domain.mapper;

import org.five.nav.domain.Article;
import org.five.nav.domain.enums.ArticleStatus;
import org.five.nav.dto.requests.ArticleRequest;
import org.five.nav.dto.responses.ArticleResponse;

public class ArticleMapper {

    public ArticleResponse from(Article article){
        return ArticleResponse.builder()
                .id(article.getId())
                .content(article.getContent())
                .title(article.getTitle())
                .build();
    }

    public Article from(ArticleRequest request){
        return Article.builder()
                .status(ArticleStatus.ACTIVE)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }
}

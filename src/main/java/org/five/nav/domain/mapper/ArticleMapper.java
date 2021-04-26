package org.five.nav.domain.mapper;

import org.five.nav.domain.Article;
import org.five.nav.domain.User;
import org.five.nav.domain.enums.ArticleStatus;
import org.five.nav.dto.requests.ArticleRequest;
import org.five.nav.dto.responses.ArticleResponse;
import org.five.nav.dto.responses.UserResponse;

import java.util.Calendar;
import java.util.UUID;

public class ArticleMapper {

    public ArticleResponse from(Article article, UserResponse userResponse){
        return ArticleResponse.builder()
                .id(article.getId())
                .content(article.getContent())
                .title(article.getTitle())
                .user(userResponse)
                .build();
    }

    public Article from(ArticleRequest request){
        return Article.builder()
                .status(ArticleStatus.ACTIVE)
                .title(request.getTitle())
                .content(request.getContent())
                .code("article_"+ UUID.randomUUID()+ Calendar.getInstance().getTimeInMillis())
                .build();
    }
}

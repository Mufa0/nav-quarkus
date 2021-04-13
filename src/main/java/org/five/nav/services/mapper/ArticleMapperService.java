package org.five.nav.services.mapper;

import org.five.nav.domain.Article;
import org.five.nav.dto.requests.ArticleRequest;
import org.five.nav.dto.responses.ArticleResponse;


public interface ArticleMapperService {

    ArticleResponse transform(Article article);

    Article transform(ArticleRequest request);

    Article transform(ArticleRequest request, Article article);

}

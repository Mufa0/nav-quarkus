package org.five.nav.services;

import org.five.nav.dto.requests.ArticleRequest;
import org.five.nav.dto.responses.ArticleResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

public interface ArticleService {

    ArticleResponse create(ArticleRequest request, SecurityContext securityContext);

    ArticleResponse get(long id);

    ArticleResponse update(long id, ArticleRequest request, SecurityContext securityContext);

    Response delete(long id, SecurityContext securityContext);

    List<ArticleResponse> get();

    List<ArticleResponse> get(SecurityContext securityContext);
}

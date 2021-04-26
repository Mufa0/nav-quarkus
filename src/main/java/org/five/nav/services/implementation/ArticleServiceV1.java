package org.five.nav.services.implementation;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.five.nav.client.audit.AuditController;
import org.five.nav.client.audit.AuditRequest;
import org.five.nav.domain.Article;
import org.five.nav.domain.User;
import org.five.nav.domain.enums.Action;
import org.five.nav.dto.requests.ArticleRequest;
import org.five.nav.dto.responses.ArticleResponse;
import org.five.nav.exceptions.article.ArticleNotFoundException;
import org.five.nav.exceptions.user.UserNotFoundException;
import org.five.nav.repository.ArticleRepository;
import org.five.nav.repository.UserRepository;
import org.five.nav.services.ArticleService;
import org.five.nav.services.mapper.ArticleMapperService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class ArticleServiceV1 implements ArticleService {

    ArticleRepository articleRepository;

    UserRepository userRepository;

    ArticleMapperService articleMapperService;

    @RestClient
    @Inject
    AuditController auditController;

    public ArticleServiceV1(ArticleRepository articleRepository, UserRepository userRepository, ArticleMapperService articleMapperService){
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.articleMapperService = articleMapperService;
    }

    @Override
    @Transactional
    public ArticleResponse create(ArticleRequest request, SecurityContext securityContext) {
        String email = securityContext.getUserPrincipal().getName();
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            Article article = articleMapperService.transform(request, user.get());
            articleRepository.persist(article);
            log.info("Saved article into databse!");
            AuditRequest auditRequest = AuditRequest.builder()
                    .action(Action.CREATE.name())
                    .message("Article successfully created")
                    .owner(article.getCode())
                    .user(article.getAuthor().getCode())
                    .build();
            auditController.create(auditRequest);
            return articleMapperService.transform(article);
        }else{
            log.error("User with provided email: {} managed to authenticate but can't be found in database",email);
            throw new UserNotFoundException(email);
        }
    }

    @Override
    public ArticleResponse get(long id) {
        Optional<Article> article = Optional.ofNullable(articleRepository.findById(id));
        if(article.isPresent()){
            return articleMapperService.transform(article.get());
        }else{
            throw new ArticleNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public ArticleResponse update(long id, ArticleRequest request, SecurityContext securityContext) {
        Optional<Article> article = Optional.ofNullable(articleRepository.findById(id));
        if(article.isPresent()){
            Article updated = articleMapperService.transform(request, article.get());
            articleRepository.persist(updated);
            return articleMapperService.transform(updated);
        }else{
            throw new ArticleNotFoundException(id);
        }

    }

    @Override
    @Transactional
    public Response delete(long id, SecurityContext securityContext) {
        if(articleRepository.deleteById(id)){
            return Response.ok().build();
        }else{
            throw new ArticleNotFoundException(id);
        }
    }

    @Override
    public List<ArticleResponse> get() {
        List<Article> articles = articleRepository.listAll();
        return articles.stream().map(x -> articleMapperService.transform(x)).collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> get(SecurityContext securityContext) {
        String email = securityContext.getUserPrincipal().getName();
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            List<Article> articles = articleRepository.listAllForUser(user.get());
            return articles.stream().map(x -> articleMapperService.transform(x)).collect(Collectors.toList());
        }else{
            log.error("User with provided email: {} managed to authenticate but can't be found in database",email);
            throw new UserNotFoundException(email);
        }
    }
}

package org.five.nav.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.five.nav.domain.Article;
import org.five.nav.domain.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ArticleRepository implements PanacheRepository<Article> {


    public List<Article> listAllForUser(User user){
        return find("author", user).list();
    }
}

package org.five.nav.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.five.nav.domain.Article;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticleRepository implements PanacheRepository<Article> {
}

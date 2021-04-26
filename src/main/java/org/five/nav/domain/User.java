package org.five.nav.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.five.nav.domain.enums.UserStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String code;

    String email;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    List<Article> articles;

    @ManyToMany
    @JoinTable(name = "user_liked_articles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"))
    List<Article> likedArticles;

}

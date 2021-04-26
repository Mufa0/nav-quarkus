package org.five.nav.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.five.nav.domain.enums.ArticleStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(name = "title")
    String title;

    @Column(name = "content")
    String content;

    @Column(name="code")
    String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    ArticleStatus status;

    @ManyToOne
    @JoinColumn(name="author")
    User author;

    @ManyToMany(mappedBy = "likedArticles")
    List<User> usersWhoLiked;

}

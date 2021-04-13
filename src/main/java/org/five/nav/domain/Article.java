package org.five.nav.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.five.nav.domain.enums.ArticleStatus;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    ArticleStatus status;

}

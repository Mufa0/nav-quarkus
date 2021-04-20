package org.five.nav.dto.responses;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {

    long id;

    String title;

    String content;

    UserResponse user;

}

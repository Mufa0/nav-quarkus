package org.five.nav.dto.requests;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {

    String title;

    String content;


}

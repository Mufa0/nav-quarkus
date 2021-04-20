package org.five.nav.dto.requests;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest implements Request{

    String title;

    String content;


}

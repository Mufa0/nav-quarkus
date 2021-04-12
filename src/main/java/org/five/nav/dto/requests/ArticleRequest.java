package org.five.nav.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class ArticleRequest {

    String title;

    String content;


}

package org.five.nav.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class ArticleResponse {

    long id;

    String title;

    String content;

}

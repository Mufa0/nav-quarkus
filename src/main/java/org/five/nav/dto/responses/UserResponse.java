package org.five.nav.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
@AllArgsConstructor
public class UserResponse {

    long id;

    String name;

    String code;

    String lastname;

    String email;

    List<Long> articles;

    String role;

    List<Long> likedArticles;

}

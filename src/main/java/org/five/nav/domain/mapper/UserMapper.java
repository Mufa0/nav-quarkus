package org.five.nav.domain.mapper;

import org.five.nav.domain.User;
import org.five.nav.dto.responses.UserResponse;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserMapper {

    public UserResponse from (User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .articles(user.getArticles() != null ? user.getArticles().stream().map(x -> x.getId()).collect(Collectors.toList()) : new ArrayList<>())
                .likedArticles(user.getLikedArticles() != null ? user.getLikedArticles().stream().map(x -> x.getId()).collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }


}

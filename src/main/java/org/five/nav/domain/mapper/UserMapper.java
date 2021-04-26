package org.five.nav.domain.mapper;

import org.five.nav.client.user.AuthUser;
import org.five.nav.domain.User;
import org.five.nav.dto.requests.UserRequest;
import org.five.nav.dto.responses.UserResponse;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserMapper {

    public UserResponse from (User user, AuthUser authUser){
        return UserResponse.builder()
                .id(user.getId())
                .code(user.getCode())
                .name(authUser.getName())
                .lastname(authUser.getLastname())
                .email(authUser.getEmail())
                .role(authUser.getEmail())
                .articles(user.getArticles() != null ? user.getArticles().stream().map(x -> x.getId()).collect(Collectors.toList()) : new ArrayList<>())
                .likedArticles(user.getLikedArticles() != null ? user.getLikedArticles().stream().map(x -> x.getId()).collect(Collectors.toList()) : new ArrayList<>())
                .build();
    }



}

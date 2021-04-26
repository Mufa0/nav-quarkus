package org.five.nav.services.mapper.implementation;

import liquibase.pro.packaged.U;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.five.nav.client.user.AuthUser;
import org.five.nav.client.user.AuthUserController;
import org.five.nav.domain.User;
import org.five.nav.domain.mapper.UserMapper;
import org.five.nav.dto.responses.UserResponse;
import org.five.nav.services.mapper.UserMapperService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Slf4j
public class UserMapperServiceV1 implements UserMapperService {
    UserMapper userMapper;

    @Inject
    @RestClient
    AuthUserController authUserController;

    public UserMapperServiceV1(){
        this.userMapper = new UserMapper();
    }

    @Override
    public UserResponse transform(User user) {

        AuthUser authUser = authUserController.get(user.getCode());
        return this.userMapper.from(user, authUser);
    }

}

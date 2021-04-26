package org.five.nav.services.mapper;

import org.five.nav.domain.User;
import org.five.nav.dto.responses.UserResponse;

public interface UserMapperService {

    UserResponse transform(User user);

}

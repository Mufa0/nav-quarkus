package org.five.nav.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class UserRequest implements Request{

    String name;

    String lastname;

    String email;

    String password;

    String role;

}

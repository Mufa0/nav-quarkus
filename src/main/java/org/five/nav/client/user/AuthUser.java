package org.five.nav.client.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {
    String code;

    String name;

    String lastname;

    String email;

    List<String> roles;
}

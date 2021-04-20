package org.five.nav.exceptions.user;

public class UserNotFoundException extends RuntimeException{

    public final String email;

    public UserNotFoundException(String email){
        this.email = email;
    }
}

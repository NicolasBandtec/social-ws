package com.sptech.socialws.domain.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("Usuário ou senha inválidos.");
    }
}

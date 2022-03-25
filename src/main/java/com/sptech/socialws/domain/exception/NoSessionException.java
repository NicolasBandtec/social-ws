package com.sptech.socialws.domain.exception;

public class NoSessionException extends RuntimeException{

    public NoSessionException() {
        super("Sessão de usuário não encontrada.");
    }
}

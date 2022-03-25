package com.sptech.socialws.domain.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException() {
        super("Publicação não encontrada.");
    }
}

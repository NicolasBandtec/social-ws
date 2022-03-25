package com.sptech.socialws.domain.exception;

public class InvalidFieldException extends RuntimeException {

    public InvalidFieldException() {
        super("Campo inválido!");
    }
}

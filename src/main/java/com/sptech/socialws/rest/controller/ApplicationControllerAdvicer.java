package com.sptech.socialws.rest.controller;

import com.sptech.socialws.domain.exception.*;
import com.sptech.socialws.domain.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvicer {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleUserAlreadyExists(UserAlreadyExistsException exception){
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleUserNotFound(UserNotFoundException exception){
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrors handleConstraintViolation(ConstraintViolationException exception){
        List<String> exceptions =  exception
                .getConstraintViolations()
                .stream()
                .map(
                    ConstraintViolation::getMessageTemplate
                )
                .collect(Collectors.toList());

        return new ApiErrors(exceptions);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleNullPointer(NullPointerException exception){
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePostNotFound(PostNotFoundException exception){
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(InvalidFieldException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleInvalidField(InvalidFieldException exception){
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(NoSessionException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleNoSession(NoSessionException exception){
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(NullValueException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiErrors handleNullValue(NullValueException exception){
        return new ApiErrors(exception.getMessage());
    }
}

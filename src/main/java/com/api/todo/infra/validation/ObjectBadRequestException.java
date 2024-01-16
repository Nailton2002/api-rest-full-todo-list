package com.api.todo.infra.validation;

public class ObjectBadRequestException extends RuntimeException{

    public ObjectBadRequestException(String message){
        super(message);
    }
}

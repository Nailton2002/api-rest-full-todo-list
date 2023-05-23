package com.api.todo.infra.validation;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(Object id) {

        super("Service não encontrado -> Id " + id);
    }
}

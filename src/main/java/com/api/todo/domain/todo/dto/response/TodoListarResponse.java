package com.api.todo.domain.todo.dto.response;

import com.api.todo.domain.todo.entity.Todo;

public record TodoListarResponse(Long id, String titulo, String descricao) {
    public TodoListarResponse(Todo todo){
        this(todo.getId(), todo.getTitulo(), todo.getDescricao());
    }
}

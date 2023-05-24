package com.api.todo.domain.todo.dto;

import com.api.todo.domain.todo.entity.Todo;

public record TodoListar(Long id, String titulo, String descricao) {
    public TodoListar(Todo todo){
        this(todo.getId(), todo.getTitulo(), todo.getDescricao());
    }
}

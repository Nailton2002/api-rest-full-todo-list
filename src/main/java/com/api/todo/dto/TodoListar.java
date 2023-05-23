package com.api.todo.dto;

import com.api.todo.domain.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TodoListar(Long id, String titulo, String descricao) {
    public TodoListar(Todo todo){
        this(todo.getId(), todo.getTitulo(), todo.getDescricao());
    }
}

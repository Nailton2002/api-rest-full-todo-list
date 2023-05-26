package com.api.todo.domain.todo.dto;

import com.api.todo.domain.todo.entity.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TodoListarPorId (
        Long id,
        String titulo,
        String descricao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd't'HH:mm:ss'Z'", timezone = "GMT")
        LocalDateTime dataTarefaFinalizada,
        Boolean tarefaFinalizada){

    public TodoListarPorId(Todo todo){
        this(todo.getId(),
             todo.getTitulo(),
             todo.getDescricao(),
             todo.getDataTarefaFinalizada(),
             todo.getTarefaFinalizada());
    }
}

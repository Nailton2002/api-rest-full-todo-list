package com.api.todo.domain.todo.dto.response;

import com.api.todo.domain.todo.entity.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TodoListarPorIdResponse(
        Long id,
        String titulo,
        String descricao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd't'HH:mm:ss'Z'", timezone = "GMT")
        LocalDateTime dataTarefaFinalizada,
        Boolean tarefaFinalizada){

    public TodoListarPorIdResponse(Todo todo){
        this(todo.getId(),
             todo.getTitulo(),
             todo.getDescricao(),
             todo.getDataTarefaFinalizada(),
             todo.getTarefaFinalizada());
    }
}

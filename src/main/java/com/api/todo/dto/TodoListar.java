package com.api.todo.dto;

import com.api.todo.domain.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TodoListar(
       Integer id,
       String titulo,
       String descricao,
       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd't'HH:mm:ss'Z'", timezone = "GMT")
       LocalDateTime dataTarefaFinalizada,
        Boolean finalizado
) {
    public TodoListar(Todo todo){
        this(todo.getId(), todo.getTitulo(), todo.getDescricao(), todo.getDataTarefaFinalizada(), todo.getTarefaFinalizada());
    }
}

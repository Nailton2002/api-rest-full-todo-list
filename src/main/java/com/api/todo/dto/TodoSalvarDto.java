package com.api.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TodoSalvarDto(
        String titulo,
        String descricao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd't'HH:mm:ss'Z'", timezone = "GMT")
        LocalDateTime dataParaFinalizar
) { }

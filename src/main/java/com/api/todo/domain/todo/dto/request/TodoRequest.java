package com.api.todo.domain.todo.dto.request;

import com.api.todo.domain.todo.entity.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {
    private Long id;
    private String titulo;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataParaFinalizar;
    private Boolean finalizado = false;

    public static TodoRequest fromEntityToDto(Todo obj) {
        return TodoRequest.builder()
                .id(obj.getId())
                .titulo(obj.getTitulo())
                .descricao(obj.getDescricao())
                .dataParaFinalizar(obj.getDataParaFinalizar())
                .finalizado(obj.getFinalizado())
                .build();

    }
}

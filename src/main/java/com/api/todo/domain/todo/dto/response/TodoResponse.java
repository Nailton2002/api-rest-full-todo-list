package com.api.todo.domain.todo.dto.response;

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
public class TodoResponse {

    private Long id;
    private String titulo;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataParaFinalizar;
    private Boolean finalizado = false;

    public static TodoResponse fromEntityToDto(Todo obj) {
        return TodoResponse.builder()
                .id(obj.getId())
                .titulo(obj.getTitulo())
                .descricao(obj.getDescricao())
                .dataParaFinalizar(obj.getDataParaFinalizar())
                .finalizado(obj.getFinalizado())
                .build();

    }
}

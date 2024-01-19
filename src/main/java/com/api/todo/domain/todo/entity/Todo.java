package com.api.todo.domain.todo.entity;

import com.api.todo.domain.todo.dto.request.TodoRequest;
import com.api.todo.domain.todo.dto.response.TodoResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_todo")
@Entity(name = "Todo")
@EqualsAndHashCode(of = "id")
public class Todo {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String titulo;
     private String descricao;
     private Date dataParaFinalizar;
     private Boolean finalizado = false;

     public static Todo fromRequestToEntity(TodoRequest dto) {
          return Todo.builder()
                  .id(dto.getId())
                  .titulo(dto.getTitulo())
                  .descricao(dto.getDescricao())
                  .dataParaFinalizar(dto.getDataParaFinalizar())
                  .finalizado(dto.getFinalizado())
                  .build();
     }

     public static Todo fromResponseToEntity(TodoResponse dto) {
          return Todo.builder()
                  .id(dto.getId())
                  .titulo(dto.getTitulo())
                  .descricao(dto.getDescricao())
                  .dataParaFinalizar(dto.getDataParaFinalizar())
                  .finalizado(dto.getFinalizado())
                  .build();
     }
     public void atualizarTarefas(TodoRequest request) {
          if (request.getTitulo() != null) {
               this.titulo = request.getTitulo();
          }
          if (request.getDescricao() != null) {
               this.descricao = request.getDescricao();
          }
          if (request.getDataParaFinalizar() != null) {
               this.dataParaFinalizar = request.getDataParaFinalizar();
          }
          if (request.getFinalizado() != null) {
               this.finalizado = request.getFinalizado();
          }
     }

     public void finalizandoTarefa(){
          this.finalizado = true;
     }
}

package com.api.todo.domain.todo.entity;

import com.api.todo.domain.todo.dto.request.TodoAtualizarRequest;
import com.api.todo.domain.todo.dto.request.TodoSalvarRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd't'HH:mm:ss'Z'", timezone = "GMT")
     private LocalDateTime dataTarefaFinalizada = LocalDateTime.now();
     private Boolean tarefaFinalizada;

     public Todo (TodoSalvarRequest dados){
          this.tarefaFinalizada = false;
          this.titulo = dados.titulo();
          this.descricao = dados.descricao();
          this.dataTarefaFinalizada = dados.dataTarefaFinalizada();
     }

     public void atualizarTarefas(TodoAtualizarRequest dados){
          if (dados.titulo() != null){
               this.titulo = dados.titulo();
          }
          if (dados.descricao() != null){
               this.descricao = dados.descricao();
          }
          if (dados.dataTarefaFinalizada() != null){
               this.dataTarefaFinalizada = dados.dataTarefaFinalizada();
          }
     }

     public void finalizandoTarefa(){
          this.tarefaFinalizada = true;
     }
}

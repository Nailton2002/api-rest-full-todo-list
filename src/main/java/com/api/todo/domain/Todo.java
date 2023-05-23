package com.api.todo.domain;

import com.api.todo.dto.TodoSalvarDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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

     public Todo (TodoSalvarDto dados){
          this.tarefaFinalizada = false;
          this.titulo = dados.titulo();
          this.descricao = dados.descricao();
          this.dataTarefaFinalizada = dados.dataParaFinalizar();
     }
}

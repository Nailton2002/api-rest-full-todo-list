package com.api.todo.repository;

import com.api.todo.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Page<Todo> findAllByTarefaFinalizadaFalse(Pageable paginacao);
    Page<Todo> findAllByTarefaFinalizadaTrue(Pageable paginacao);

    //TAREFA INICIADA = ABERTA
    @Query("SELECT obj FROM Todo obj WHERE obj.tarefaFinalizada = false ORDER BY obj.dataTarefaFinalizada")
    List<Todo> findAllOpen();

    //TAREFA FINALIZADA = FECHADA
    @Query("SELECT obj FROM Todo obj WHERE obj.tarefaFinalizada = true ORDER BY obj.dataTarefaFinalizada")
    List<Todo> findAllClose();

}

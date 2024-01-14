package com.api.todo.domain.todo.repository;

import com.api.todo.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("UPDATE Todo t SET t.tarefaFinalizada = true WHERE t.id =:id")
    Todo updateTaskById(Long id);

    @Query("SELECT t FROM Todo t WHERE t.tarefaFinalizada = false")
    Page<Todo> findAllByTarefaFinalizadaFalse(Pageable paginacao);

    @Query("SELECT t FROM Todo t WHERE t.tarefaFinalizada = true")
    Page<Todo> findAllByTarefaFinalizadaTrue(Pageable paginacao);

    //TAREFA INICIADA = ABERTA
    @Query("SELECT obj FROM Todo obj WHERE obj.tarefaFinalizada = false ORDER BY obj.dataTarefaFinalizada ASC")
    List<Todo> findAllOpen();

    //TAREFA FINALIZADA = FECHADA
    @Query("SELECT obj FROM Todo obj WHERE obj.tarefaFinalizada = true ORDER BY obj.dataTarefaFinalizada ASC")
    List<Todo> findAllClose();

}

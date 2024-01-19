package com.api.todo.domain.todo.repository;

import com.api.todo.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("SELECT obj FROM Todo obj WHERE obj.finalizado = false ORDER BY obj.dataParaFinalizar ASC")
    List<Todo> findAllOpen();

    @Query("SELECT obj FROM Todo obj WHERE obj.finalizado = true ORDER BY obj.dataParaFinalizar ASC")
    List<Todo> findAllClose();

}

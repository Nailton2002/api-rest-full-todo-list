package com.api.todo.service;

import com.api.todo.domain.Todo;
import com.api.todo.dto.TodoSalvarDto;
import com.api.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public Todo create(TodoSalvarDto dados){
        return repository.save(new Todo(dados));
    }
}

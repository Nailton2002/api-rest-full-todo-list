package com.api.todo.service;

import com.api.todo.domain.Todo;
import com.api.todo.dto.TodoListar;
import com.api.todo.dto.TodoSalvarDto;
import com.api.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public Todo create(TodoSalvarDto dados){
        return repository.save(new Todo(dados));
    }

    public List<TodoListar> findAll(){
        List<Todo> list = repository.findAll();
        List<TodoListar> dto = list.stream().map(t -> new TodoListar(t)).collect(Collectors.toList());
        return dto;
    }
}

package com.api.todo.service;

import com.api.todo.domain.Todo;
import com.api.todo.dto.TodoListar;
import com.api.todo.dto.TodoListarPorId;
import com.api.todo.dto.TodoSalvar;
import com.api.todo.infra.validation.ObjectNotFoundException;
import com.api.todo.infra.validation.ResourceNotFoundException;
import com.api.todo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public Todo create(TodoSalvar dados){
        return repository.save(new Todo(dados));
    }

    public List<TodoListar> findAll(){
        List<Todo> list = repository.findAll();
        List<TodoListar> dto = list.stream().map(t -> new TodoListar(t)).collect(Collectors.toList());
        return dto;
    }

    public Page<Todo> findAllByTarefaFinalizadaFalse(Pageable paginacao){
        return repository.findAllByTarefaFinalizadaFalse(paginacao);
    }

     public Page<Todo> findAllByTarefaFinalizadaTrue(Pageable paginacao){
        return repository.findAllByTarefaFinalizadaTrue(paginacao);
    }

    public TodoListarPorId findByid(Long id){
        var todo = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(id));
        return new TodoListarPorId(todo);
    }

    public Todo update(Long id){
        if (repository.existsById(id) == true){
            Optional<Todo> obj = Optional.of(repository.getReferenceById(id));
            return obj.orElseThrow(()-> new ObjectNotFoundException(id));
        } else {
            throw new ObjectNotFoundException(id);
        }
    }

    public void delete(Long id) {
        if (repository.existsById(id) == true){
            repository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id);
        }
    }

    public void finalizandoTarefa(Long id){
        var todo = repository.getReferenceById(id);
        if (update(id).getTarefaFinalizada() == false){
            todo.finalizandoTarefa();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

}

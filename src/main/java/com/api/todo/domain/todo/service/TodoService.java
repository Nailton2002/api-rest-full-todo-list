package com.api.todo.domain.todo.service;

import com.api.todo.domain.todo.entity.Todo;
import com.api.todo.domain.todo.dto.response.TodoListarResponse;
import com.api.todo.domain.todo.dto.response.TodoListarPorIdResponse;
import com.api.todo.domain.todo.dto.request.TodoSalvarRequest;
import com.api.todo.infra.validation.ObjectBadRequestException;
import com.api.todo.infra.validation.ObjectNotFoundException;
import com.api.todo.infra.validation.ResourceNotFoundException;
import com.api.todo.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public Todo create(TodoSalvarRequest dados) {
        try {
            Todo obj = new Todo(dados);
            obj = repository.save(new Todo(dados));
            return obj;
        } catch (Exception e) {
            throw new ObjectBadRequestException("Erro ao cria tarefa: " + e);
        }
    }

    @Transactional
    public List<TodoListarResponse> findAll() {
        List<Todo> list = repository.findAll();
        List<TodoListarResponse> dto = list.stream().map(t -> new TodoListarResponse(t)).collect(Collectors.toList());
        return dto;
    }

    @Transactional
    public List<TodoListarPorIdResponse> findAllOpen() {
        List<Todo> list = repository.findAllOpen();
        List<TodoListarPorIdResponse> dto = list.stream().map(t -> new TodoListarPorIdResponse(t)).collect(Collectors.toList());
        return dto;
    }

    @Transactional
    public List<TodoListarPorIdResponse> findAllClose() {
        List<Todo> list = repository.findAllClose();
        List<TodoListarPorIdResponse> dto = list.stream().map(t -> new TodoListarPorIdResponse(t)).collect(Collectors.toList());
        return dto;
    }

    @Transactional
    public Page<Todo> findAllByTarefaFinalizadaFalse(Pageable paginacao) {
        return repository.findAllByTarefaFinalizadaFalse(paginacao);
    }

    @Transactional
    public Page<Todo> findAllByTarefaFinalizadaTrue(Pageable paginacao) {
        return repository.findAllByTarefaFinalizadaTrue(paginacao);
    }

    @Transactional
    public TodoListarPorIdResponse findByid(Long id) {
        var todo = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        return new TodoListarPorIdResponse(todo);
    }

    @Transactional
    public Todo updateTaskById(Long id) {
        if (repository.existsById(id) == true) {
            Optional<Todo> obj = Optional.of(repository.getReferenceById(id));
            return obj.orElseThrow(() -> new ObjectNotFoundException(id));
        } else {
            throw new ObjectNotFoundException(id);
        }
    }

    public void delete(Long id) {
        if (repository.existsById(id) == true) {
            repository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id);
        }
    }

    public void finalizandoTarefa(Long id) {
        var todo = repository.getReferenceById(id);
        if (updateTaskById(id).getTarefaFinalizada() == false) {
            todo.finalizandoTarefa();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

}

package com.api.todo.domain.todo.service;

import com.api.todo.domain.todo.dto.request.TodoRequest;
import com.api.todo.domain.todo.dto.response.TodoResponse;
import com.api.todo.domain.todo.entity.Todo;
import com.api.todo.domain.todo.repository.TodoRepository;
import com.api.todo.infra.validation.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public TodoResponse findById(Long id) {
        Todo obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + TodoResponse.class.getName()));
        return TodoResponse.fromEntityToDto(obj);
    }

    public List<TodoResponse> findAllOpen() {
        List<Todo> list = repository.findAllOpen();
        List<TodoResponse> responseList = list.stream().map(todo -> TodoResponse.fromEntityToDto(todo)).collect(Collectors.toList());
        return responseList;
    }

    public List<TodoResponse> findAllClose() {
        List<Todo> list = repository.findAllClose();
        List<TodoResponse> responseList = list.stream().map(todo -> TodoResponse.fromEntityToDto(todo)).collect(Collectors.toList());
        return responseList;
    }

    public List<TodoResponse> findAll() {
        List<Todo> list = repository.findAll();
        List<TodoResponse> responseList = list.stream().map(todo -> TodoResponse.fromEntityToDto(todo)).collect(Collectors.toList());
        return responseList;
    }

    public TodoResponse create(TodoRequest request) {
        Todo todo = Todo.fromRequestToEntity(request);
        Todo obj = repository.save(todo);
        return TodoResponse.fromEntityToDto(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public TodoResponse update(Long id, TodoRequest request) {
        Todo obj = Todo.fromResponseToEntity(findById(id));
        obj.atualizarTarefas(request);
        Todo todo = repository.save(obj);
        return TodoResponse.fromEntityToDto(todo);
    }

    public TodoResponse update(TodoRequest request) {
        Optional<Todo> usuarioOptional = repository.findById(request.getId());
        if (usuarioOptional.isPresent()) {
            Todo obj = usuarioOptional.get();
            // Chame o método de atualização na entidade
            obj.atualizarTarefas(request);
            Todo todoAtualizado = repository.save(obj);
            return TodoResponse.fromEntityToDto(todoAtualizado);
        } else {
            throw new ObjectNotFoundException("Usuário não encontrado com o ID: " + request.getId());
        }
    }

}

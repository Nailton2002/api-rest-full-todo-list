package com.api.todo.domain.todo.service;

import com.api.todo.domain.todo.dto.request.TodoRequest;
import com.api.todo.domain.todo.dto.response.TodoResponse;
import com.api.todo.domain.todo.entity.Todo;
import com.api.todo.domain.todo.repository.TodoRepository;
import com.api.todo.infra.validation.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    @Transactional
    public TodoResponse create(TodoRequest request) {
        Todo todo = Todo.fromRequestToEntity(request);
        Todo obj = repository.save(todo);
        return TodoResponse.fromEntityToDto(obj);
    }

    @Transactional
    public List<TodoResponse> findAll() {
        List<Todo> list = repository.findAll();
        List<TodoResponse> responseList = list.stream().map(todo -> TodoResponse.fromEntityToDto(todo)).collect(Collectors.toList());
        return responseList;
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

    @Transactional
    public TodoResponse findById(Long id) {
        Todo obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + TodoResponse.class.getName()));
        return TodoResponse.fromEntityToDto(obj);
    }

    @Transactional
    public TodoResponse update(TodoRequest request) {
        Optional<Todo> usuarioOptional = repository.findById(request.getId());
        if (usuarioOptional.isPresent()) {
            Todo obj = usuarioOptional.get();
            obj.atualizarTarefas(request);
            Todo todoAtualizado = repository.save(obj);
            return TodoResponse.fromEntityToDto(todoAtualizado);
        } else {
            throw new ObjectNotFoundException("Usuário não encontrado com o ID: " + request.getId());
        }
    }

    public Todo exclusionLogic(Long id){
        Optional<Todo> obj = Optional.of(repository.getReferenceById(id));
        Todo todo = new Todo();
        todo.finalizandoTarefa();
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + Todo.fromResponseToEntity(findById(id))));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}

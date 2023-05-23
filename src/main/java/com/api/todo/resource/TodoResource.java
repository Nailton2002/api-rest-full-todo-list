package com.api.todo.resource;

import com.api.todo.domain.Todo;
import com.api.todo.dto.TodoAtualizar;
import com.api.todo.dto.TodoListar;
import com.api.todo.dto.TodoListarPorId;
import com.api.todo.dto.TodoSalvar;
import com.api.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoResource {
    @Autowired
    private TodoService service;

    @Transactional
    @PostMapping(value = "/todos")
    public ResponseEntity create(@RequestBody @Valid TodoSalvar dados, UriComponentsBuilder uriComponentsBuilder){
        service.create(dados);
        var uri = uriComponentsBuilder.path("/{id}").buildAndExpand(dados.getClass()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoListar>> listar(){
        List<TodoListar> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/todos/pages/open")
    public ResponseEntity<Page<TodoListar>> listarPorPaginacaoFechada(@PageableDefault(size = 5, sort = {"titulo"})Pageable paginacao){
        var page = service.findAllByTarefaFinalizadaFalse(paginacao).map(TodoListar::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/todos/pages/close")
    public ResponseEntity<Page<TodoListar>> listarPorPaginacaoAberta(@PageableDefault(size = 5, sort = {"titulo"})Pageable paginacao){
        var page = service.findAllByTarefaFinalizadaTrue(paginacao).map(TodoListar::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<TodoListarPorId> findById(@PathVariable Long id){
        TodoListarPorId obj = service.findByid(id);
        return ResponseEntity.ok().body(obj);
    }

    @Transactional
    @PutMapping("/todos/{id}")
    public ResponseEntity atualizar(@RequestBody TodoAtualizar dados){
        var todo = service.update(dados.id());
        todo.atualizarTarefas(dados);
        return ResponseEntity.ok(new TodoListarPorId(todo));
    }

}

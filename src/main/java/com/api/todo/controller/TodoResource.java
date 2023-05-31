package com.api.todo.controller;

import com.api.todo.domain.todo.dto.TodoAtualizar;
import com.api.todo.domain.todo.dto.TodoListar;
import com.api.todo.domain.todo.dto.TodoListarPorId;
import com.api.todo.domain.todo.dto.TodoSalvar;
import com.api.todo.domain.todo.service.TodoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@Profile("dev")
@RequestMapping("/todos")
@SecurityRequirement(name = "bearer-key")
public class TodoResource {
    @Autowired
    private TodoService service;

    @Transactional
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid TodoSalvar dados, UriComponentsBuilder uriComponentsBuilder){
        var obj =  service.create(dados);
        var uri = uriComponentsBuilder.path("/todos/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new TodoListarPorId(obj));
    }

    @GetMapping
    public ResponseEntity<List<TodoListar>> listar(){
        List<TodoListar> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/false/iniciadas")
    public ResponseEntity<List<TodoListarPorId>> listOpen() {
        List<TodoListarPorId> list = service.findAllOpen();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/true/finalizadas")
    public ResponseEntity<List<TodoListarPorId>> listClose() {
        List<TodoListarPorId> list = service.findAllClose();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/pages/opens")
    public ResponseEntity<Page<TodoListar>> listarPorPaginacaoFechada(@PageableDefault(size = 5,
    sort = {"titulo"}) Pageable paginacao){
        var page = service.findAllByTarefaFinalizadaFalse(paginacao).map(TodoListar::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/pages/closes")
    public ResponseEntity<Page<TodoListar>> listarPorPaginacaoAberta(@PageableDefault(size = 5,
     sort = {"titulo"}) Pageable paginacao){
        var page = service.findAllByTarefaFinalizadaTrue(paginacao).map(TodoListar::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoListarPorId> findById(@PathVariable Long id){
        TodoListarPorId obj = service.findByid(id);
        return ResponseEntity.ok().body(obj);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@RequestBody TodoAtualizar dados){
        var todo = service.update(dados.id());
        todo.atualizarTarefas(dados);
        return ResponseEntity.ok(new TodoListarPorId(todo));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/finalizadas/{id}")
    public ResponseEntity finalizandoTarefa(@PathVariable Long id){
        service.finalizandoTarefa(id);
        return ResponseEntity.noContent().build();
    }

}

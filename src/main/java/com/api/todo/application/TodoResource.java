package com.api.todo.application;

import com.api.todo.application.doc.TodoResourceDoc;
import com.api.todo.domain.todo.dto.request.TodoAtualizarRequest;
import com.api.todo.domain.todo.dto.request.TodoSalvarRequest;
import com.api.todo.domain.todo.dto.response.TodoListarPorIdResponse;
import com.api.todo.domain.todo.dto.response.TodoListarResponse;
import com.api.todo.domain.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Profile("dev")
@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
@SecurityRequirement(name = "bearer-key")
public class TodoResource implements TodoResourceDoc {

    private final TodoService service;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid TodoSalvarRequest dados, UriComponentsBuilder uriComponentsBuilder) {
        var obj = service.create(dados);
        var uri = uriComponentsBuilder.path("/todos/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new TodoListarPorIdResponse(obj));
    }

    @GetMapping
    public ResponseEntity<List<TodoListarResponse>> listar() {
        List<TodoListarResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/false/iniciadas")
    public ResponseEntity<List<TodoListarPorIdResponse>> listOpen() {
        List<TodoListarPorIdResponse> list = service.findAllOpen();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/true/finalizadas")
    public ResponseEntity<List<TodoListarPorIdResponse>> listClose() {
        List<TodoListarPorIdResponse> list = service.findAllClose();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/pages/opens")
    public ResponseEntity<Page<TodoListarResponse>> listarPorPaginacaoFechada(@PageableDefault(size = 5,
            sort = {"titulo"}) Pageable paginacao) {
        var page = service.findAllByTarefaFinalizadaFalse(paginacao).map(TodoListarResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/pages/closes")
    public ResponseEntity<Page<TodoListarResponse>> listarPorPaginacaoAberta(@PageableDefault(size = 5,
            sort = {"titulo"}) Pageable paginacao) {
        var page = service.findAllByTarefaFinalizadaTrue(paginacao).map(TodoListarResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoListarPorIdResponse> findById(@PathVariable Long id) {
        TodoListarPorIdResponse obj = service.findByid(id);
        return ResponseEntity.ok().body(obj);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@RequestBody TodoAtualizarRequest dados) {
        var todo = service.updateTaskById(dados.id());
        todo.atualizarTarefas(dados);
        return ResponseEntity.ok(new TodoListarPorIdResponse(todo));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/finalizadas/{id}")
    public ResponseEntity finalizandoTarefa(@PathVariable Long id) {
        service.finalizandoTarefa(id);
        return ResponseEntity.noContent().build();
    }

}

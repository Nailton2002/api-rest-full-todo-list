package com.api.todo.application;

import com.api.todo.application.doc.TodoResourceDoc;
import com.api.todo.domain.todo.dto.request.TodoRequest;
import com.api.todo.domain.todo.dto.response.TodoResponse;
import com.api.todo.domain.todo.service.TodoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Profile("dev")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/todos")
@SecurityRequirement(name = "bearer-key")
public class TodoResource implements TodoResourceDoc {

    private final TodoService service;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        TodoResponse response = service.create(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> findAll() {
        List<TodoResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/open")
    public ResponseEntity<List<TodoResponse>> findAllOpen() {
        List<TodoResponse> list = service.findAllOpen();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/close")
    public ResponseEntity<List<TodoResponse>> findAllClose() {
        List<TodoResponse> list = service.findAllClose();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TodoResponse> findById(@PathVariable Long id) {
        TodoResponse dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) {
        request.setId(id);
        TodoResponse usuarioAtualizado = service.update(request);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/exclusionLogic/{id}")
    public ResponseEntity<Void> exclusionLogic(@PathVariable Long id) {
        service.exclusionLogic(id);
        return ResponseEntity.noContent().build();
    }

}

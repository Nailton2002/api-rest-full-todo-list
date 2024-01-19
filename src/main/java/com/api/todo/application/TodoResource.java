package com.api.todo.application;

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
public class TodoResource {

    private final TodoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TodoResponse> findById(@PathVariable Long id) {
        TodoResponse dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/open")
    public ResponseEntity<List<TodoResponse>> listOpen() {
        List<TodoResponse> list = service.findAllOpen();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/close")
    public ResponseEntity<List<TodoResponse>> listClose() {
        List<TodoResponse> list = service.findAllClose();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> listAll() {
        List<TodoResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        TodoResponse response = service.create(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest dto) {
        TodoResponse newObj = service.update(id, dto);
        return ResponseEntity.ok().body(newObj);
    }

//	@PutMapping("/{id}")
//	public ResponseEntity<TodoResponse> update(@PathVariable Integer id, @RequestBody TodoRequest request) {
//		request.setId(id);
//		TodoResponse usuarioAtualizado = service.update(request);
//		return ResponseEntity.ok(usuarioAtualizado);
//	}

}

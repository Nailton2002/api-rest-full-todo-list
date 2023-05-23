package com.api.todo.resource;

import com.api.todo.dto.TodoSalvarDto;
import com.api.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class TodoResource {
    @Autowired
    private TodoService service;

    @Transactional
    @PostMapping(value = "/todos")
    public ResponseEntity<TodoSalvarDto> create(@RequestBody @Valid TodoSalvarDto dados, UriComponentsBuilder uriComponentsBuilder){
        service.create(dados);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(dados.id()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

}

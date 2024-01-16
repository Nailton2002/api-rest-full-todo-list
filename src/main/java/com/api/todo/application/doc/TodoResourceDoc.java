package com.api.todo.application.doc;

import com.api.todo.domain.todo.dto.request.TodoAtualizarRequest;
import com.api.todo.domain.todo.dto.request.TodoSalvarRequest;
import com.api.todo.domain.todo.dto.response.TodoListarPorIdResponse;
import com.api.todo.domain.todo.dto.response.TodoListarResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface TodoResourceDoc {

    @Operation(summary = "Salva novos produtos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao salvar os produtos"),
    })
    ResponseEntity create(@RequestBody @Valid TodoSalvarRequest dados, UriComponentsBuilder uriComponentsBuilder);

    @Operation(summary = "Busca uma lista de todoas as tarefas que foram feitas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de tarefas feitas.")
    })
    ResponseEntity<List<TodoListarResponse>> listar();

    @Operation(summary = "Busca uma lista de tarefas que não foi feita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma tarefas por id."),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity<List<TodoListarPorIdResponse>> listOpen();

    @Operation(summary = "Busca uma lista de tarefas que foi feita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma tarefas por id."),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity<List<TodoListarPorIdResponse>> listClose();

    @Operation(summary = "Busca uma lista de tarefas paginas que foi feita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma tarefas por id."),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity<Page<TodoListarResponse>> listarPorPaginacaoFechada(@PageableDefault(size = 5, sort = {"titulo"}) Pageable paginacao);

    @Operation(summary = "Busca uma lista de tarefas paginas que não foi feita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma tarefas por id."),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity<Page<TodoListarResponse>> listarPorPaginacaoAberta(@PageableDefault(size = 5, sort = {"titulo"}) Pageable paginacao);

    @Operation(summary = "Busca uma tarefas que foi feita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma tarefas por id."),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity<TodoListarPorIdResponse> findById(@PathVariable Long id);

    @Operation(summary = "Atualiza uma tarefas que foi feita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma tarefas por id."),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity atualizar(@RequestBody TodoAtualizarRequest dados);

    @Operation(summary = "Deleta uma tarefas que foi feita por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = ""),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity delete(@PathVariable Long id);

    @Operation(summary = "Inativa uma tarefas que foi feita por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = ""),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    ResponseEntity finalizandoTarefa(@PathVariable Long id);
}

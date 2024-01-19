//package com.api.todo.domain.todo.dto.response;
//
//import com.api.todo.domain.todo.entity.Todo;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//public record TodoResponseOld(
//        Long id,
//        String titulo,
//        String descricao,
//        LocalDateTime dataParaFinalizar,
//        Boolean finalizado){
//
//    public TodoResponseOld(Todo obj){
//        this(obj.getId(), obj.getTitulo(), obj.getDescricao(), obj.getDataParaFinalizar(), obj.getFinalizado());
//    }
//
//}

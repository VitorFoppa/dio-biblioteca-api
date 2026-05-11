package com.vitor.biblioteca.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> tratarRecursoNaoEncontrado(
            ResourceNotFoundException ex) {

        Map<String, Object> erro = new HashMap<>();

        erro.put("timestamp", LocalDateTime.now());
        erro.put("status", 404);
        erro.put("erro", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacoes(
            MethodArgumentNotValidException ex) {

        Map<String, Object> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            erros.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> tratarErroGenerico(
            Exception ex) {

        Map<String, Object> erro = new HashMap<>();

        erro.put("timestamp", LocalDateTime.now());
        erro.put("status", 500);
        erro.put("erro", "Erro interno do servidor");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
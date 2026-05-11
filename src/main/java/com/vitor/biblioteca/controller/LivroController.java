package com.vitor.biblioteca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.biblioteca.domain.service.LivroService;
import com.vitor.biblioteca.dto.LivroRequestDTO;
import com.vitor.biblioteca.dto.LivroResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<LivroResponseDTO> listarTodos() {
        return livroService.listarTodos();
    }

    @GetMapping("/{id}")
    public LivroResponseDTO buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id);
    }

    @PostMapping
    public LivroResponseDTO criar(@RequestBody @Valid LivroRequestDTO dto) {
        return livroService.criar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid LivroRequestDTO dto) {

        LivroResponseDTO livroAtualizado = livroService.atualizar(id, dto);

        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        livroService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
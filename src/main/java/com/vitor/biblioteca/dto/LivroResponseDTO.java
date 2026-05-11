package com.vitor.biblioteca.dto;

public record LivroResponseDTO(
        Long id,
        String titulo,
        String isbn,
        Integer anoPublicacao,
        Integer quantidadePaginas,
        String autor,
        String categoria
) {
}
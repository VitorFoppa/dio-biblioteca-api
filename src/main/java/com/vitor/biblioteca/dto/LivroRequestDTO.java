package com.vitor.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LivroRequestDTO(

        @NotBlank(message = "Título é obrigatório")
        String titulo,

        @NotBlank(message = "ISBN é obrigatório")
        @Size(min = 10, max = 13, message = "ISBN deve ter entre 10 e 13 caracteres")
        String isbn,

        @NotNull(message = "Ano de publicação é obrigatório")
        Integer anoPublicacao,

        @NotNull(message = "Quantidade de páginas é obrigatória")
        @Positive(message = "Quantidade de páginas deve ser positiva")
        Integer quantidadePaginas,

        @NotNull(message = "Autor é obrigatório")
        Long autorId,

        @NotNull(message = "Categoria é obrigatória")
        Long categoriaId

) {}
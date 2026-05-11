package com.vitor.biblioteca.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vitor.biblioteca.domain.model.Autor;
import com.vitor.biblioteca.domain.model.Categoria;
import com.vitor.biblioteca.domain.model.Livro;
import com.vitor.biblioteca.domain.repository.AutorRepository;
import com.vitor.biblioteca.domain.repository.CategoriaRepository;
import com.vitor.biblioteca.domain.repository.LivroRepository;
import com.vitor.biblioteca.dto.LivroRequestDTO;
import com.vitor.biblioteca.dto.LivroResponseDTO;
import com.vitor.biblioteca.exception.ResourceNotFoundException;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    private LivroResponseDTO converterParaDTO(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.getQuantidadePaginas(),
                livro.getAutor().getNome(),
                livro.getCategoria().getNome()
        );
    }

    public LivroService(
            LivroRepository livroRepository,
            AutorRepository autorRepository,
            CategoriaRepository categoriaRepository
    ) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));

        Autor autor = autorRepository.findById(dto.autorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setQuantidadePaginas(dto.quantidadePaginas());
        livro.setAutor(autor);
        livro.setCategoria(categoria);

        Livro livroAtualizado = livroRepository.save(livro);

        return converterParaDTO(livroAtualizado);
    }

    public LivroResponseDTO criar(LivroRequestDTO dto) {

    Autor autor = autorRepository.findById(dto.autorId())
            .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));

    Categoria categoria = categoriaRepository.findById(dto.categoriaId())
            .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

    Livro livro = new Livro();

    livro.setTitulo(dto.titulo());
    livro.setIsbn(dto.isbn());
    livro.setAnoPublicacao(dto.anoPublicacao());
    livro.setQuantidadePaginas(dto.quantidadePaginas());
    livro.setAutor(autor);
    livro.setCategoria(categoria);

    Livro livroSalvo = livroRepository.save(livro);

    return converterParaDTO(livroSalvo);
    }

    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Long id) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));

        return converterParaDTO(livro);
    }

    public Livro atualizar(Long id, Livro livroAtualizado) {

        Optional<Livro> livroExistente = livroRepository.findById(id);

        if (livroExistente.isPresent()) {

            Livro livro = livroExistente.get();

            livro.setTitulo(livroAtualizado.getTitulo());
            livro.setIsbn(livroAtualizado.getIsbn());
            livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
            livro.setQuantidadePaginas(livroAtualizado.getQuantidadePaginas());
            livro.setAutor(livroAtualizado.getAutor());
            livro.setCategoria(livroAtualizado.getCategoria());

            return livroRepository.save(livro);
        }

        return null;
    }

    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }
}
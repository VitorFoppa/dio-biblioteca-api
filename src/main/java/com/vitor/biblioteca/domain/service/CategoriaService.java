package com.vitor.biblioteca.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vitor.biblioteca.domain.model.Categoria;
import com.vitor.biblioteca.domain.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria criar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria categoriaAtualizada) {

        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);

        if (categoriaExistente.isPresent()) {

            Categoria categoria = categoriaExistente.get();

            categoria.setNome(categoriaAtualizada.getNome());
            categoria.setDescricao(categoriaAtualizada.getDescricao());

            return categoriaRepository.save(categoria);
        }

        return null;
    }

    public void deletar(Long id) {
        categoriaRepository.deleteById(id);
    }
}
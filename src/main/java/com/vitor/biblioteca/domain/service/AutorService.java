package com.vitor.biblioteca.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vitor.biblioteca.domain.model.Autor;
import com.vitor.biblioteca.domain.repository.AutorRepository;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Optional<Autor> buscarPorId(Long id) {
        return autorRepository.findById(id);
    }

    public Autor criar(Autor autor) {
        return autorRepository.save(autor);
    }

    public Autor atualizar(Long id, Autor autorAtualizado) {

        Optional<Autor> autorExistente = autorRepository.findById(id);

        if (autorExistente.isPresent()) {

            Autor autor = autorExistente.get();

            autor.setNome(autorAtualizado.getNome());
            autor.setNacionalidade(autorAtualizado.getNacionalidade());

            return autorRepository.save(autor);
        }

        return null;
    }

    public void deletar(Long id) {
        autorRepository.deleteById(id);
    }
}
package com.vitor.biblioteca.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.biblioteca.domain.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
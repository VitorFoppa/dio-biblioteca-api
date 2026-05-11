package com.vitor.biblioteca.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.biblioteca.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
package com.vitor.biblioteca.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.biblioteca.domain.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
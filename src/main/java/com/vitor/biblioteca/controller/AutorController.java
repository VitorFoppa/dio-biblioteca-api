package com.vitor.biblioteca.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.biblioteca.domain.model.Autor;
import com.vitor.biblioteca.domain.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public List<Autor> listarTodos() {
        return autorService.listarTodos();
    }

    @PostMapping
    public Autor criar(@RequestBody Autor autor) {
        return autorService.criar(autor);
    }
}
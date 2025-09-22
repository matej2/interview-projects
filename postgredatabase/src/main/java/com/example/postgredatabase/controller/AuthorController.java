package com.example.postgredatabase.controller;

import com.example.postgredatabase.domain.Author;
import com.example.postgredatabase.repositories.AuthorRepository;
import com.example.postgredatabase.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, AuthorService authorService) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    @GetMapping
    public Iterable<Author> getAuthor() {
        return authorService.getAllAuthors();
    }

    @PostMapping
    public void createAuthor(@RequestBody Author author) {
        authorService.createAuthor(author);
    }
}

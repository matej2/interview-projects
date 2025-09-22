package com.example.postgredatabase.controller;

import com.example.postgredatabase.domain.Author;
import com.example.postgredatabase.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public Iterable<Author> getAuthor() {
        return authorRepository.findAll();
    }

    @PostMapping
    public void createAuthor(@RequestBody Author author) {
        authorRepository.save(author);
    }
}

package com.example.postgredatabase.controller;

import com.example.postgredatabase.domain.dto.AuthorDTO;
import com.example.postgredatabase.domain.entities.AuthorEntity;
import com.example.postgredatabase.mappers.impl.AuthorMapper;
import com.example.postgredatabase.repositories.AuthorRepository;
import com.example.postgredatabase.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, AuthorService authorService, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @GetMapping
    public List<AuthorDTO> getAuthor() {
        Iterable<AuthorEntity> results = authorService.getAllAuthors();
        return StreamSupport.stream(results.spliterator(), false)
                .map(authorMapper::mapTo)
                .toList();

    }

    @PostMapping
    public void createAuthor(@RequestBody AuthorDTO author) {
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        authorService.createAuthor(authorEntity);

    }
}

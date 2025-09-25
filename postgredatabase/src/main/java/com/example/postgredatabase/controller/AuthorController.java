package com.example.postgredatabase.controller;

import com.example.postgredatabase.domain.dto.AuthorDTO;
import com.example.postgredatabase.domain.entities.AuthorEntity;
import com.example.postgredatabase.mappers.impl.AuthorMapper;
import com.example.postgredatabase.repositories.AuthorRepository;
import com.example.postgredatabase.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final AuthorServiceImpl authorServiceImpl;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, AuthorServiceImpl authorServiceImpl, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorServiceImpl = authorServiceImpl;
        this.authorMapper = authorMapper;
    }

    @GetMapping
    public Page<AuthorDTO> listAuthors(Pageable pageable) {
        Page<AuthorEntity> results = authorServiceImpl.findAll(pageable);
        return results.map(authorMapper::mapTo);
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody AuthorDTO author) {
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity resultOfAuthorCreation = authorServiceImpl.createAuthor(authorEntity);
        return authorMapper.mapTo(resultOfAuthorCreation);

    }
}

package com.example.postgredatabase.service;

import com.example.postgredatabase.domain.dto.AuthorDTO;
import com.example.postgredatabase.domain.entities.AuthorEntity;
import com.example.postgredatabase.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Iterable<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

    public AuthorEntity createAuthor(AuthorEntity author) {
        return authorRepository.save(author);
    }
}

package com.example.postgredatabase.service;

import com.example.postgredatabase.domain.Author;
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

    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public void createAuthor(Author author) {
        authorRepository.save(author);
    }
}

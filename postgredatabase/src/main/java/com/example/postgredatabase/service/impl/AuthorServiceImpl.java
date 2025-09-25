package com.example.postgredatabase.service.impl;

import com.example.postgredatabase.domain.entities.AuthorEntity;
import com.example.postgredatabase.repositories.AuthorRepository;
import com.example.postgredatabase.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;


@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorEntity> getAllAuthors() {
        return StreamSupport.stream(authorRepository
                                .findAll()
                                .spliterator(),
                        false)
                .toList();
    }

    @Override
    public Page<AuthorEntity> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity author) {
        return authorRepository.save(author);
    }
}

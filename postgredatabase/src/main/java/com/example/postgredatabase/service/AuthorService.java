package com.example.postgredatabase.service;

import com.example.postgredatabase.domain.entities.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    Iterable<AuthorEntity> getAllAuthors();

    Page<AuthorEntity> findAll(Pageable pageable);

    AuthorEntity createAuthor(AuthorEntity author);
}

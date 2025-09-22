package com.example.postgredatabase.repositories;

import com.example.postgredatabase.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepositoriy extends CrudRepository<Author, Long> {
}

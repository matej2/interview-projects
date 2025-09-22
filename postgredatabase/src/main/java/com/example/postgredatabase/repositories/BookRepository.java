package com.example.postgredatabase.repositories;

import com.example.postgredatabase.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, String> {
}

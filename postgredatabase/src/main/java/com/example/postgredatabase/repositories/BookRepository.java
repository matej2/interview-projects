package com.example.postgredatabase.repositories;

import com.example.postgredatabase.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}

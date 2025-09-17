package com.example.postgredatabase.dao.impl;

import com.example.postgredatabase.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDAOImpl {
    private final JdbcTemplate jdbcTemplate;

    public BookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn,title,author_id) VALUES (?,?,?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId()
        );
    }
}

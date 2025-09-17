package com.example.postgredatabase.dao.impl;

import com.example.postgredatabase.dao.AuthorDAO;
import com.example.postgredatabase.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDAOImpl implements AuthorDAO {
    private final JdbcTemplate jdbcTemplate;

    public AuthorDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void create(Author author) {
        jdbcTemplate.update(
                "INSERT INTO authors (id,name,age) VALUES (?,?,?)",
                author.getId(),
                author.getName(),
                author.getAge());
    }
}

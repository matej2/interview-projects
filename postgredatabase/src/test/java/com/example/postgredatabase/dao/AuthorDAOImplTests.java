package com.example.postgredatabase.dao;

import com.example.postgredatabase.dao.impl.AuthorDAOImpl;
import com.example.postgredatabase.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDAOImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDAOImpl authorDAOImpl;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Author author = Author.builder()
                .id(1L)
                .age(22)
                .name("John")
                .build();
        authorDAOImpl.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id,name,age) VALUES (?,?,?)"),
                eq(1L),
                eq("John"),
                eq(22));
    }
}

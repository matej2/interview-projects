package com.example.postgredatabase.repositories;

import com.example.postgredatabase.domain.entities.AuthorEntity;
import com.example.postgredatabase.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookEntityRepositoryIntegrationTests {

    private final BookRepository bookRepository;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    public void testThatCreateBookGeneratesCorrectSql() {
        AuthorEntity author = AuthorEntity.builder()
                .age(22)
                .name("John")
                .build();

        BookEntity book = BookEntity.builder()
                .title("Test")
                .isbn("test_isbn")
                .author(author)
                .build();

        bookRepository.save(book);

        final Optional<BookEntity> result = bookRepository.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get().getIsbn()).isEqualTo(book.getIsbn());
    }
}

package com.example.postgredatabase.repositories;

import com.example.postgredatabase.domain.Author;
import com.example.postgredatabase.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookRepositoryIntegrationTests {

    private final BookRepository bookRepository;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    public void testThatCreateBookGeneratesCorrectSql() {
        Author author = Author.builder()
                .age(22)
                .name("John")
                .build();

        Book book = Book.builder()
                .title("Test")
                .isbn("test_isbn")
                .author(author)
                .build();

        bookRepository.save(book);

        final Optional<Book> result = bookRepository.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get().getIsbn()).isEqualTo(book.getIsbn());
    }
}

package com.example.postgredatabase.repositories;

import com.example.postgredatabase.domain.Author;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorRepositoryIntegrationTests {

    private final AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreated() {
        Author author = Author.builder()
                .age(22)
                .name("John")
                .build();
        underTest.save(author);

        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorGetAuthorsWithAgeLessThan() {
        Author authorA = Author.builder()
                .age(35)
                .name("John")
                .build();
        underTest.save(authorA);

        Author authorB = Author.builder()
                .age(22)
                .name("John")
                .build();

        underTest.save(authorB);

        Iterable<Author> results = underTest.ageLessThan(30);
        assertThat(results).containsExactly(authorB);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        Author authorA = Author.builder()
                .age(35)
                .name("John")
                .build();
        underTest.save(authorA);

        Author authorB = Author.builder()
                .age(22)
                .name("John")
                .build();

        underTest.save(authorB);

        Iterable<Author> results = underTest.findAuthorsWithAgeGreaterThan(30);

        assertThat(results).containsExactly(authorA);


    }
}

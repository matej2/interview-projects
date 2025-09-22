package com.example.postgredatabase.repositories;

import com.example.postgredatabase.domain.entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorEntityRepositoryIntegrationTests {

    private final AuthorRepository underTest;

    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreated() {
        AuthorEntity author = AuthorEntity.builder()
                .age(22)
                .name("John")
                .build();
        underTest.save(author);

        Optional<AuthorEntity> result = underTest.findById(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorGetAuthorsWithAgeLessThan() {
        AuthorEntity authorA = AuthorEntity.builder()
                .age(35)
                .name("John")
                .build();
        underTest.save(authorA);

        AuthorEntity authorB = AuthorEntity.builder()
                .age(22)
                .name("John")
                .build();

        underTest.save(authorB);

        Iterable<AuthorEntity> results = underTest.ageLessThan(30);
        assertThat(results).containsExactly(authorB);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        AuthorEntity authorA = AuthorEntity.builder()
                .age(35)
                .name("John")
                .build();
        underTest.save(authorA);

        AuthorEntity authorB = AuthorEntity.builder()
                .age(22)
                .name("John")
                .build();

        underTest.save(authorB);

        Iterable<AuthorEntity> results = underTest.findAuthorsWithAgeGreaterThan(30);

        assertThat(results).containsExactly(authorA);


    }
}

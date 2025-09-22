package com.example.postgredatabase.repositories;

import com.example.postgredatabase.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorRepositoryIntegrationTests {

    private final AuthorRepositoriy underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepositoriy underTest) {
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
}

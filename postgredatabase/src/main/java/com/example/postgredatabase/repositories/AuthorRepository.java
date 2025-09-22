package com.example.postgredatabase.repositories;

import com.example.postgredatabase.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    Iterable<AuthorEntity> ageLessThan(int i);

    @Query("SELECT a FROM AuthorEntity a WHERE a.age > ?1")
    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int i);
}

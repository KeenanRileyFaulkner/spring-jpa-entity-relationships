package com.keena.dbrelationships.repository;

import com.keena.dbrelationships.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

package com.keena.dbrelationships.repository;

import com.keena.dbrelationships.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

package com.keena.dbrelationships.repository;

import com.keena.dbrelationships.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

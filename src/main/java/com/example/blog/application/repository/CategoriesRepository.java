package com.example.blog.application.repository;

import com.example.blog.application.model.categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<categories, Long> {
}

package com.example.blog.application.repository;

import com.example.blog.application.model.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blogs, Long> {

    // Using @Query to be explicit about the relationship
    @Query("SELECT b FROM Blogs b WHERE b.categories.category_name = :categoryName")
    List<Blogs> findBlogsByCategoryName(@Param("categoryName") String categoryName);

    List<Blogs> findByTitle(String title);
    List<Blogs> findByAuthor(String author);
}
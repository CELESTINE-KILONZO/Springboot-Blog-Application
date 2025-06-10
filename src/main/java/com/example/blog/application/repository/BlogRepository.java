package com.example.blog.application.repository;

import com.example.blog.application.model.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BlogRepository  extends JpaRepository<Blogs, Long> {

    List<Blogs> findByCategories(String categories);
    List<Blogs> findByTitle(String title);
    List<Blogs> findByAuthor(String author);
}

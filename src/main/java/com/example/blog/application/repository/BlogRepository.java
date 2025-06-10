package com.example.blog.application.repository;

import com.example.blog.application.model.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRepository  extends JpaRepository<Blogs, Long> {
}

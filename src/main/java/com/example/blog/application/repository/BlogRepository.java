package com.example.blog.application.repository;

import com.example.blog.application.model.blogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository  extends JpaRepository<blogs, Long> {
}

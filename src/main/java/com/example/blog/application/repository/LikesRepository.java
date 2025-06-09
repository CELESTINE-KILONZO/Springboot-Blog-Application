package com.example.blog.application.repository;

import com.example.blog.application.model.likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository <likes,Long> {
}

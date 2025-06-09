package com.example.blog.application.repository;

import com.example.blog.application.model.comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsReposiory extends JpaRepository<comments,Long> {
}

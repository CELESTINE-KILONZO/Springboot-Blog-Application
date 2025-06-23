package com.example.blog.application.repository;

import com.example.blog.application.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface CommentsRepository {

    @Repository
    public interface CommentsReposiory extends JpaRepository<Comments,Long> {
    }
}

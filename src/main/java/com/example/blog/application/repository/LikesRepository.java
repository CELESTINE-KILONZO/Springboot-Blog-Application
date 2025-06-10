package com.example.blog.application.repository;

import com.example.blog.application.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository <Likes,Long> {
}

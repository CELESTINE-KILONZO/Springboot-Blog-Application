package com.example.blog.application.repository;

import com.example.blog.application.model.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<users, Long> {
}

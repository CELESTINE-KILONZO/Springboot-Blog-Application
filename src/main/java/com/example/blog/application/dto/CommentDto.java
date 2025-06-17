package com.example.blog.application.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long comment_id;
    private String content;
    private LocalDateTime created_at;

    // User Info
    private Long user_id;
    private String username;
    private String email;

    // Blog Info
    private Long blog_id;
    private String title;
    private String author;
}

package com.example.blog.application.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikesDto {
    private Long like_id;
    private LocalDateTime liked_at;

    // User Info
    private Long user_id;
    private String username;
    private String email;

    // Blog Info
    private Long blog_id;
    private String title;
}

package com.example.blog.application.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogUserCatDto {
    private Long blog_id;
    private String title;
    private String description;
    private String content;

    // Author/User Info
    private Long user_id;
    private String username;
    private String email;

    // Category Info
    private Long category_id;
    private String category_name;

    // Timestamps
    private LocalDateTime created;
    private LocalDateTime updated;
}


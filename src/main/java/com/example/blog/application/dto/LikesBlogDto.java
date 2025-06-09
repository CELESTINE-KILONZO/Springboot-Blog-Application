package com.example.blog.application.dto;

import java.time.LocalDateTime;

public class LikesBlogDto {
    private Long blog_id;
    private String title;
    private String  author;
    private Long like_id;
    private LocalDateTime liked_at;
}

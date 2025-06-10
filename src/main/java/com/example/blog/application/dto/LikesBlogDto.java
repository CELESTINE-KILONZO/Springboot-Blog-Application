package com.example.blog.application.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class LikesBlogDto {
    private Long blog_id;
    private String title;
    private String  author;
    private Long like_id;
    private LocalDateTime liked_at;
}

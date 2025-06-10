package com.example.blog.application.dto;

import lombok.Data;

@Data
public class BlogUserDto {
    private Long user_id;
    private String username;
    private String email;
    private Long blog_id;
    private String title;
    private String  author;
}

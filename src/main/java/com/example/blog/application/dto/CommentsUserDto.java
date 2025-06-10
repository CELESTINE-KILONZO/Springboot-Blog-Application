package com.example.blog.application.dto;

import lombok.Data;

@Data
public class CommentsUserDto {
    private Long user_id;
    private String username;
    private String email;
    private Long comment_id;
    private String content;
}

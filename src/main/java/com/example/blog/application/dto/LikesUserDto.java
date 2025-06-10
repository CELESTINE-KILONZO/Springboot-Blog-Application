package com.example.blog.application.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikesUserDto {
    private Long user_id;
    private String username;
    private String email;
    private Long like_id;
    private LocalDateTime liked_at;
}

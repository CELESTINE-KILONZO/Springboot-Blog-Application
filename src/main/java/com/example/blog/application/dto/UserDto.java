package com.example.blog.application.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long user_id;
    private String username;
    private String email;
    private String role;
}


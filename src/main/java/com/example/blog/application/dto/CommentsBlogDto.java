package com.example.blog.application.dto;

import lombok.Data;

@Data
public class CommentsBlogDto {
    private Long blog_id;
    private String title;
    private String  author;
    private Long comment_id;
    private String content;
}

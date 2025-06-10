package com.example.blog.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

    @Entity
    @Table(name = "comments")
    @Getter
    @Setter
    @NoArgsConstructor

    public class Comments {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long comment_id;
        private String content;
        private LocalDateTime created_at;


        @ManyToOne(fetch=FetchType.EAGER,optional=false)
        @JoinColumn(name = "user_id")
        private Users users;

        @ManyToOne(fetch=FetchType.EAGER,optional=false)
        @JoinColumn(name = "blog_id")
        private Blogs blogs;
    }






package com.example.blog.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class comments {
    @Entity
    @Table(name = "comments")
    @Getter
    @Setter
    @NoArgsConstructor

    public static class comments  {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String content;
        private LocalDateTime created_at;



    }

}

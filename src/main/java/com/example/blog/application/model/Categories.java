package com.example.blog.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Entity
    @Table (name = "categories")
    @Getter
    @Setter
    @NoArgsConstructor
    public class Categories {
        @Id
        private Long category_id;
        private String category_name;

    }


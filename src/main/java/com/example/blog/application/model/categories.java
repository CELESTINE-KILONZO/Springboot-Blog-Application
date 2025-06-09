package com.example.blog.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Entity
    @Table (name = "categories")
    @Getter
    @Setter
  @NoArgsConstructor
    public class categories {
        @Id
        private Long category_id;
        private String category_name;



    }


package com.example.blog.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class categories {
    @Entity
    @Table (name = "categories")
    @Getter
    @Setter
  @NoArgsConstructor
    public static class categories {
        @Id
        private Long id;
        private String name;



    }
}

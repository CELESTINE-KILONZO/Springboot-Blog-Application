package com.example.blog.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class users {
    @Entity
    @Table
    @Getter
    @Setter
    @NoArgsConstructor

    public static class users {
        @Id
        private Long user_id;
        private String username;
        private String email;
        private String password;
        private String role;
    }


}

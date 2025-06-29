package com.example.blog.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Entity
    @Table(name="users")
    @Getter
    @Setter
    @NoArgsConstructor

    public class Users {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long user_id;
        private String username;
        private String email;
        private String password;
        private String role;

        public Users(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }
    }




package com.example.blog.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@NoArgsConstructor
public class Blogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blog_id;
    private String title;
    private String description;
    private String  author;
    private String  content;
    private String category;
    private LocalDateTime created;
    private LocalDateTime updated;

    @ManyToOne(fetch=FetchType.EAGER,optional=false)
    @JoinColumn(name = "user_id")
    private Users users;


    @ManyToOne(fetch=FetchType.EAGER,optional=false)
    @JoinColumn(name = "category_id")
    private Categories categories;
}


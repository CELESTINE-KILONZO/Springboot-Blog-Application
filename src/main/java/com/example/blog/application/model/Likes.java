package com.example.blog.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name ="likes")
@Getter
@Setter
@NoArgsConstructor

public class Likes {
    @Id
    private Long like_id;
    private LocalDateTime liked_at;

    @ManyToOne(fetch= FetchType.EAGER,optional=false)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch=FetchType.EAGER,optional=false)
    @JoinColumn(name = "blog_id")
    private Blogs blogs;
}



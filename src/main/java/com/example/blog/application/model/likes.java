package com.example.blog.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="likes")
@Getter
@Setter
@NoArgsConstructor

public class likes {
    @Id
    private Long id;
    private
}

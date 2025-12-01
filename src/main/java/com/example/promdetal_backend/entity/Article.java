package com.example.promdetal_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articles")
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 20000)
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;


    @OneToOne
    @JoinColumn(name = "cover_image_id")
    private FileInfo coverImage;

    @ManyToMany
    @JoinTable(
            name = "article_recommendations",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "recommended_id")
    )
    private List<Article> recommended = new ArrayList<>();
}

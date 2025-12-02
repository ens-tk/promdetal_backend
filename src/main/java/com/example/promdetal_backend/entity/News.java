package com.example.promdetal_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@Data
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 20000)
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Картинка новости (иконка)
    @OneToOne
    @JoinColumn(name = "cover_image_id")
    private FileInfo coverImage;
}

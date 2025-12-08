package com.example.promdetal_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "partners")
@Data
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "image_id")
    private FileInfo image;
}

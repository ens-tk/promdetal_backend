package com.example.promdetal_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hotspot")
@Data
public class Hotspot {

    @Id
    @GeneratedValue()
    private Long id;

    private double x; // координата X (0-100 %)
    private double y; // координата Y (0-100 %)
    private String text;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private FileInfo file;
}

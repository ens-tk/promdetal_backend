package com.example.promdetal_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "files")
@Data
public class FileInfo {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String extension;

    private Long size;

    private String path;


    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hotspot> hotspots = new ArrayList<>();
}

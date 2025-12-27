package com.example.promdetal_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "equipment")
@Data
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String shortDescription;

    @Column(length = 10000)
    private String fullDescription;

    private String slug;

    private boolean showOnMain;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "equipment_id")
    private List<FileInfo> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "group_id")
    private EquipmentGroup group;
}

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

    @Column(unique = true)
    private String slug;

    private boolean showOnMain;

    /** Rutube URL */
    private String videoUrl;

    /** Основное изображение */
    @ManyToOne
    @JoinColumn(name = "main_image_id")
    private FileInfo mainImage;

    /** Изображение для hotspot */
    @ManyToOne
    @JoinColumn(name = "hotspot_image_id")
    private FileInfo hotspotImage;

    /** Преимущества */
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipmentAdvantage> advantages = new ArrayList<>();

    /** Hotspots */
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hotspot> hotspots = new ArrayList<>();

    /** Ключевые слова */
    @ElementCollection
    private List<String> searchKeywords = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "group_id")
    private EquipmentGroup group;
}

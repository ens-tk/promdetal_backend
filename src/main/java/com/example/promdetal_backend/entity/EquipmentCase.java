package com.example.promdetal_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "equipment_cases")
@Data
public class EquipmentCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer year;

    private String city;
    private String customer;
    private String equipmentType;

    private String services;

    @Column(length = 5000)
    private String problem;

    @Column(length = 5000)
    private String solution;

    @Column(length = 5000)
    private String result;

    @OneToOne
    @JoinColumn(name = "image_id")
    private FileInfo image;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private EquipmentGroup group;
}


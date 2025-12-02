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

    private String customer;

    private LocalDate deliveryDate;

    private String equipmentType;

    @Column(length = 5000)
    private String purpose1;

    @Column(length = 5000)
    private String purpose2;

    @Column(length = 5000)
    private String purpose3;

    @OneToOne
    @JoinColumn(name = "image_id")
    private FileInfo image;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
}


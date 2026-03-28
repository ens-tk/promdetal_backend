package com.example.promdetal_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class EquipmentAdvantage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iconId;
    private String text;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
}

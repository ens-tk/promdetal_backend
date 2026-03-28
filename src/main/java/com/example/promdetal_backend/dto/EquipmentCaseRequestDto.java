package com.example.promdetal_backend.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.PastOrPresent;

@Data
public class EquipmentCaseRequestDto {
    private Long groupId;
    private String title;

    private Integer year;

    private String city;
    private String customer;
    private String equipmentType;
    private String services;
    private String problem;
    private String solution;
    private String result;

    private UUID imageId;
}




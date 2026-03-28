package com.example.promdetal_backend.dto;

import com.example.promdetal_backend.entity.FileInfo;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class EquipmentCaseResponseDto {

    private Long id;

    private Long groupId;
    private String groupTitle;

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



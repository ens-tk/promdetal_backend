package com.example.promdetal_backend.dto;

import com.example.promdetal_backend.entity.FileInfo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EquipmentCaseRequestDto {

    private String customer;
    private LocalDate deliveryDate;
    private String equipmentType;

    private String purpose1;
    private String purpose2;
    private String purpose3;

    private FileInfo image;
}

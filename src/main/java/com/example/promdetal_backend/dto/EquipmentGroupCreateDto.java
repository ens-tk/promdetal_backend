package com.example.promdetal_backend.dto;

import com.example.promdetal_backend.entity.FileInfo;
import lombok.Data;


@Data
public class EquipmentGroupCreateDto {
    private String title;
    private String description;
    private FileInfo coverImage;
}

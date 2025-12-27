package com.example.promdetal_backend.dto;

import com.example.promdetal_backend.entity.FileInfo;
import lombok.Data;

import java.util.List;

@Data
public class EquipmentGroupDto {
    private Long id;
    private String title;
    private String description;
    private FileInfo coverImage;
    private List<EquipmentDto> equipmentList;
}




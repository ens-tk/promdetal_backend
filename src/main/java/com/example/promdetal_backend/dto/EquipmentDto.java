package com.example.promdetal_backend.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EquipmentDto {
    private Long id;
    private String title;
    private String shortDescription;
    private String fullDescription;
    private String slug;
    private boolean showOnMain;
    private Long groupId;
    private List<UUID> imageIds;
}

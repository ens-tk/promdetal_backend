package com.example.promdetal_backend.mapper;

import com.example.promdetal_backend.dto.EquipmentDto;
import com.example.promdetal_backend.entity.Equipment;
import com.example.promdetal_backend.entity.FileInfo;

import java.util.stream.Collectors;

public class EquipmentMapper {

    public static EquipmentDto toDto(Equipment equipment) {
        EquipmentDto dto = new EquipmentDto();
        dto.setId(equipment.getId());
        dto.setTitle(equipment.getTitle());
        dto.setShortDescription(equipment.getShortDescription());
        dto.setFullDescription(equipment.getFullDescription());
        dto.setSlug(equipment.getSlug());
        dto.setShowOnMain(equipment.isShowOnMain());

        if (equipment.getGroup() != null) {
            dto.setGroupId(equipment.getGroup().getId());
        }

        dto.setImageIds(
                equipment.getImages().stream()
                        .map(FileInfo::getId)
                        .collect(Collectors.toList())
        );

        return dto;
    }
}

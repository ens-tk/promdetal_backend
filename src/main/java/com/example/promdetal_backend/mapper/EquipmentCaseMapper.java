package com.example.promdetal_backend.mapper;

import com.example.promdetal_backend.dto.EquipmentCaseRequestDto;
import com.example.promdetal_backend.dto.EquipmentCaseResponseDto;
import com.example.promdetal_backend.entity.EquipmentCase;
import com.example.promdetal_backend.entity.EquipmentGroup;
import com.example.promdetal_backend.entity.FileInfo;

import java.time.Year;

import com.example.promdetal_backend.dto.EquipmentCaseRequestDto;
import com.example.promdetal_backend.dto.EquipmentCaseResponseDto;
import com.example.promdetal_backend.entity.EquipmentCase;

import java.time.Year;
import java.util.UUID;

public class EquipmentCaseMapper {

    public static void updateEntity(EquipmentCase entity, EquipmentCaseRequestDto dto, EquipmentGroup group) {
        if (dto.getYear() != null) {
            int currentYear = Year.now().getValue();
            if (dto.getYear() > currentYear) {
                throw new IllegalArgumentException("Год не может быть из будущего");
            }
            entity.setYear(dto.getYear());
        }

        entity.setTitle(dto.getTitle());
        entity.setCity(dto.getCity());
        entity.setCustomer(dto.getCustomer());
        entity.setEquipmentType(dto.getEquipmentType());
        entity.setServices(dto.getServices());
        entity.setProblem(dto.getProblem());
        entity.setSolution(dto.getSolution());
        entity.setResult(dto.getResult());

        // ✅ Сохраняем изображение
        if (dto.getImageId() != null) {
            FileInfo file = new FileInfo();
            file.setId(dto.getImageId()); // предполагаем, что достаточно установить id
            entity.setImage(file);
        }
        if (group != null) {
            entity.setGroup(group);
        }
    }

    public static EquipmentCaseResponseDto toDto(EquipmentCase entity) {
        EquipmentCaseResponseDto dto = new EquipmentCaseResponseDto();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setYear(entity.getYear());          // ✅ ВАЖНО
        dto.setCity(entity.getCity());
        dto.setCustomer(entity.getCustomer());
        dto.setEquipmentType(entity.getEquipmentType());
        dto.setServices(entity.getServices());
        dto.setProblem(entity.getProblem());
        dto.setSolution(entity.getSolution());
        dto.setResult(entity.getResult());

        if (entity.getImage() != null) {
            dto.setImageId(entity.getImage().getId()); // ✅ UUID
        }

        if (entity.getGroup() != null) {
            dto.setGroupId(entity.getGroup().getId());
            dto.setGroupTitle(entity.getGroup().getTitle());
        }

        return dto;
    }
}


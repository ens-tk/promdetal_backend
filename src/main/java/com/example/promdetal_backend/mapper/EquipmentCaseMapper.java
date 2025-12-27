package com.example.promdetal_backend.mapper;

import com.example.promdetal_backend.dto.EquipmentCaseRequestDto;
import com.example.promdetal_backend.dto.EquipmentCaseResponseDto;
import com.example.promdetal_backend.entity.EquipmentCase;

public class EquipmentCaseMapper {

    public static EquipmentCaseResponseDto toDto(EquipmentCase c) {
        EquipmentCaseResponseDto dto = new EquipmentCaseResponseDto();

        dto.setId(c.getId());
        dto.setCustomer(c.getCustomer());
        dto.setDeliveryDate(c.getDeliveryDate());
        dto.setEquipmentType(c.getEquipmentType());
        dto.setPurpose1(c.getPurpose1());
        dto.setPurpose2(c.getPurpose2());
        dto.setPurpose3(c.getPurpose3());
        dto.setImage(c.getImage());

        if (c.getEquipment() != null) {
            dto.setEquipmentId(c.getEquipment().getId());
            dto.setEquipmentTitle(c.getEquipment().getTitle());
        }

        return dto;
    }

    public static void updateEntity(EquipmentCase entity, EquipmentCaseRequestDto dto) {
        entity.setCustomer(dto.getCustomer());
        entity.setDeliveryDate(dto.getDeliveryDate());
        entity.setEquipmentType(dto.getEquipmentType());
        entity.setPurpose1(dto.getPurpose1());
        entity.setPurpose2(dto.getPurpose2());
        entity.setPurpose3(dto.getPurpose3());
        entity.setImage(dto.getImage());
    }
}

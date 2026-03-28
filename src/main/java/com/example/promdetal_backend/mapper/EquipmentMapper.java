package com.example.promdetal_backend.mapper;

import com.example.promdetal_backend.dto.*;
import com.example.promdetal_backend.entity.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EquipmentMapper {

    /* ===================== ENTITY → DTO ===================== */

    public static EquipmentDto toDto(Equipment equipment) {
        EquipmentDto dto = new EquipmentDto();

        dto.setId(equipment.getId());
        dto.setTitle(equipment.getTitle());
        dto.setShortDescription(equipment.getShortDescription());
        dto.setFullDescription(equipment.getFullDescription());
        dto.setSlug(equipment.getSlug());
        dto.setShowOnMain(equipment.isShowOnMain());
        dto.setVideoUrl(equipment.getVideoUrl());

        // Группа
        if (equipment.getGroup() != null) {
            dto.setGroupId(equipment.getGroup().getId());
        }

        // Изображения
        if (equipment.getMainImage() != null) {
            dto.setMainImageId(equipment.getMainImage().getId());
        }

        if (equipment.getHotspotImage() != null) {
            dto.setHotspotImageId(equipment.getHotspotImage().getId());
        }

        // Преимущества
        dto.setAdvantages(
                equipment.getAdvantages().stream()
                        .map(a -> {
                            AdvantageDto ad = new AdvantageDto();
                            ad.setId(a.getId());
                            ad.setText(a.getText());
                            ad.setIconId(a.getIconId()); // <- вот так правильно
                            return ad;
                        })
                        .toList()
        );


        // Hotspots
        dto.setHotspots(
                equipment.getHotspots().stream()
                        .map(h -> {
                            HotspotDto hd = new HotspotDto();
                            hd.setId(h.getId());
                            hd.setX(h.getX());
                            hd.setY(h.getY());
                            hd.setText(h.getText());
                            return hd;
                        })
                        .collect(Collectors.toList())
        );

        // Ключевые слова → строка
        dto.setSearchKeywords(String.join(", ", equipment.getSearchKeywords()));

        return dto;
    }

    /* ===================== DTO → ENTITY ===================== */

    public static void updateEntity(Equipment equipment, EquipmentDto dto) {

        equipment.setTitle(dto.getTitle());
        equipment.setShortDescription(dto.getShortDescription());
        equipment.setFullDescription(dto.getFullDescription());
        equipment.setSlug(dto.getSlug());
        equipment.setShowOnMain(dto.isShowOnMain());
        equipment.setVideoUrl(dto.getVideoUrl());

        // Ключевые слова: строка → List<String>
        equipment.getSearchKeywords().clear();
        if (dto.getSearchKeywords() != null && !dto.getSearchKeywords().isBlank()) {
            List<String> keywords = Arrays.stream(dto.getSearchKeywords().split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();
            equipment.getSearchKeywords().addAll(keywords);
        }

        // ⚠ group / images / advantages / hotspots
        // обновляются в сервисе, НЕ в mapper
    }
}

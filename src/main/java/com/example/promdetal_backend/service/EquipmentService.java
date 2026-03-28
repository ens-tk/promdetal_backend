package com.example.promdetal_backend.service;

import com.example.promdetal_backend.dto.EquipmentDto;
import com.example.promdetal_backend.entity.*;
import com.example.promdetal_backend.mapper.EquipmentMapper;
import com.example.promdetal_backend.repository.EquipmentRepository;
import com.example.promdetal_backend.repository.FileInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.example.promdetal_backend.repository.EquipmentGroupRepository;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final FileInfoRepository fileInfoRepository;
    private final EquipmentGroupRepository groupRepository;


    public List<EquipmentDto> getAll(Long groupId) {
        List<Equipment> list;

        if (groupId != null) {
            EquipmentGroup group = groupRepository.findById(groupId)
                    .orElseThrow(() -> new RuntimeException("Group not found"));
            list = equipmentRepository.findByGroup(group);
        } else {
            list = equipmentRepository.findAll();
        }

        return list.stream()
                .map(EquipmentMapper::toDto)
                .toList();
    }

    public EquipmentDto getById(Long id) {
        return equipmentRepository.findById(id)
                .map(EquipmentMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
    }

    public List<EquipmentDto> getMainEquipment() {
        return equipmentRepository.findByShowOnMainTrue().stream()
                .map(EquipmentMapper::toDto)
                .toList();
    }


    public EquipmentDto create(EquipmentDto dto) {
        Equipment equipment = new Equipment();

        applyDtoToEntity(dto, equipment);

        return EquipmentMapper.toDto(equipmentRepository.save(equipment));
    }


    public EquipmentDto update(Long id, EquipmentDto dto) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        applyDtoToEntity(dto, equipment);

        return EquipmentMapper.toDto(equipmentRepository.save(equipment));
    }

    public void delete(Long id) {
        equipmentRepository.deleteById(id);
    }



    private void applyDtoToEntity(EquipmentDto dto, Equipment equipment) {

        equipment.setTitle(dto.getTitle());
        equipment.setShortDescription(dto.getShortDescription());
        equipment.setFullDescription(dto.getFullDescription());
        equipment.setSlug(dto.getSlug());
        equipment.setShowOnMain(dto.isShowOnMain());
        equipment.setVideoUrl(dto.getVideoUrl());

        if (dto.getGroupId() != null) {
            equipment.setGroup(
                    groupRepository.findById(dto.getGroupId())
                            .orElseThrow(() -> new RuntimeException("Group not found"))
            );
        }

        if (dto.getMainImageId() != null) {
            equipment.setMainImage(
                    fileInfoRepository.findById(dto.getMainImageId()).orElseThrow()
            );
        }

        if (dto.getHotspotImageId() != null) {
            equipment.setHotspotImage(
                    fileInfoRepository.findById(dto.getHotspotImageId()).orElseThrow()
            );
        }

        // keywords
        // Keywords
        equipment.setSearchKeywords(
                dto.getSearchKeywords() != null
                        ? new ArrayList<>(Arrays.stream(dto.getSearchKeywords().split(","))
                        .map(String::trim)
                        .toList())
                        : new ArrayList<>()
        );

// Advantages
        // Преимущества
        equipment.getAdvantages().clear(); // очищаем старые элементы
        if (dto.getAdvantages() != null) {
            dto.getAdvantages().forEach(advDto -> {
                EquipmentAdvantage adv = new EquipmentAdvantage();
                adv.setIconId(advDto.getIconId());
                adv.setText(advDto.getText());
                adv.setEquipment(equipment);
                equipment.getAdvantages().add(adv);
            });
        }

// Точки
        equipment.getHotspots().clear();
        if (dto.getHotspots() != null) {
            dto.getHotspots().forEach(h -> {
                Hotspot hotspot = new Hotspot();
                hotspot.setX(h.getX());
                hotspot.setY(h.getY());
                hotspot.setText(h.getText());
                hotspot.setEquipment(equipment);
                equipment.getHotspots().add(hotspot);
            });
        }




    }

}



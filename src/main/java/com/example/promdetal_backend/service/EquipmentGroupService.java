package com.example.promdetal_backend.service;

import com.example.promdetal_backend.dto.EquipmentDto;
import com.example.promdetal_backend.dto.EquipmentGroupCreateDto;
import com.example.promdetal_backend.dto.EquipmentGroupDto;
import com.example.promdetal_backend.entity.Equipment;
import com.example.promdetal_backend.entity.EquipmentGroup;
import com.example.promdetal_backend.repository.EquipmentGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentGroupService {

    private final EquipmentGroupRepository groupRepository;

    public List<EquipmentGroupDto> getAll() {
        return groupRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public EquipmentGroupDto getById(Long id) {
        return toDto(groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found")));
    }

    public EquipmentGroupDto create(EquipmentGroupCreateDto dto) {
        EquipmentGroup group = new EquipmentGroup();
        group.setTitle(dto.getTitle());
        group.setDescription(dto.getDescription());
        group.setCoverImage(dto.getCoverImage());
        return toDto(groupRepository.save(group));
    }

    public EquipmentGroupDto update(Long id, EquipmentGroupCreateDto dto) {
        EquipmentGroup group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        group.setTitle(dto.getTitle());
        group.setDescription(dto.getDescription());
        group.setCoverImage(dto.getCoverImage());

        return toDto(groupRepository.save(group));
    }

    public void delete(Long id) {
        EquipmentGroup group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        groupRepository.delete(group);
    }

    private EquipmentGroupDto toDto(EquipmentGroup group) {
        EquipmentGroupDto dto = new EquipmentGroupDto();
        dto.setId(group.getId());
        dto.setTitle(group.getTitle());
        dto.setDescription(group.getDescription());
        dto.setCoverImage(group.getCoverImage());
        dto.setEquipmentList(group.getEquipments().stream().map(this::toEquipmentDto).collect(Collectors.toList()));
        return dto;
    }

    private EquipmentDto toEquipmentDto(Equipment equipment) {
        EquipmentDto dto = new EquipmentDto();
        dto.setId(equipment.getId());
        dto.setTitle(equipment.getTitle());
        dto.setShortDescription(equipment.getShortDescription());
        dto.setFullDescription(equipment.getFullDescription());
        dto.setSlug(equipment.getSlug());
        dto.setShowOnMain(equipment.isShowOnMain());
        return dto;
    }
}

package com.example.promdetal_backend.service;

import com.example.promdetal_backend.dto.EquipmentDto;
import com.example.promdetal_backend.entity.Equipment;
import com.example.promdetal_backend.entity.FileInfo;
import com.example.promdetal_backend.mapper.EquipmentMapper;
import com.example.promdetal_backend.repository.EquipmentRepository;
import com.example.promdetal_backend.repository.FileInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.example.promdetal_backend.entity.EquipmentGroup;
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

    public EquipmentDto getBySlug(String slug) {
        return equipmentRepository.findBySlug(slug)
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


    public EquipmentDto addImage(Long equipmentId, UUID fileId) {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        FileInfo file = fileInfoRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));

        equipment.getImages().add(file);

        return EquipmentMapper.toDto(equipmentRepository.save(equipment));
    }


    private void applyDtoToEntity(EquipmentDto dto, Equipment equipment) {
        equipment.setTitle(dto.getTitle());
        equipment.setShortDescription(dto.getShortDescription());
        equipment.setFullDescription(dto.getFullDescription());
        equipment.setSlug(dto.getSlug());
        equipment.setShowOnMain(dto.isShowOnMain());

        if (dto.getGroupId() != null) {
            EquipmentGroup group = groupRepository.findById(dto.getGroupId())
                    .orElseThrow(() -> new RuntimeException("Group not found"));
            equipment.setGroup(group);
        }
    }
}



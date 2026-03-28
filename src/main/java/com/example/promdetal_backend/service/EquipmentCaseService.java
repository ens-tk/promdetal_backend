package com.example.promdetal_backend.service;

import com.example.promdetal_backend.dto.EquipmentCaseRequestDto;
import com.example.promdetal_backend.dto.EquipmentCaseResponseDto;
import com.example.promdetal_backend.entity.Equipment;
import com.example.promdetal_backend.entity.EquipmentCase;
import com.example.promdetal_backend.entity.EquipmentGroup;
import com.example.promdetal_backend.mapper.EquipmentCaseMapper;
import com.example.promdetal_backend.repository.EquipmentCaseRepository;
import com.example.promdetal_backend.repository.EquipmentGroupRepository;
import com.example.promdetal_backend.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentCaseService {

    private final EquipmentCaseRepository caseRepository;
    private final EquipmentGroupRepository groupRepository;

    // Получение всех кейсов
    public List<EquipmentCaseResponseDto> getAllCases() {
        return caseRepository.findAll()
                .stream()
                .map(EquipmentCaseMapper::toDto) // ✅ здесь подключаем Mapper
                .toList();
    }

    // Получение кейсов по группе
    public List<EquipmentCaseResponseDto> getByGroup(Long groupId) {
        return caseRepository.findByGroupId(groupId)
                .stream()
                .map(EquipmentCaseMapper::toDto)
                .toList();
    }

    // Получение одного кейса
    public EquipmentCaseResponseDto getCase(Long id) {
        EquipmentCase entity = caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found"));
        return EquipmentCaseMapper.toDto(entity);
    }

    // Создание кейса через DTO
    public EquipmentCaseResponseDto createCase(Long groupId, EquipmentCaseRequestDto dto) {
        EquipmentGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        EquipmentCase entity = new EquipmentCase();
        EquipmentCaseMapper.updateEntity(entity, dto, group); // ✅ передаём группу прямо в маппер

        EquipmentCase saved = caseRepository.save(entity);
        return EquipmentCaseMapper.toDto(saved);
    }


    // Обновление кейса через DTO
    public EquipmentCaseResponseDto updateCase(Long id, EquipmentCaseRequestDto dto) {
        EquipmentCase existing = caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        // Подгружаем группу из dto.getGroupId()
        EquipmentGroup group = null;
        if (dto.getGroupId() != null) {
            group = groupRepository.findById(dto.getGroupId())
                    .orElseThrow(() -> new RuntimeException("Group not found"));
        }

        EquipmentCaseMapper.updateEntity(existing, dto, group);
        EquipmentCase saved = caseRepository.save(existing);
        return EquipmentCaseMapper.toDto(saved);
    }


    public void deleteCase(Long id) {
        caseRepository.deleteById(id);
    }
}

package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.dto.EquipmentCaseRequestDto;
import com.example.promdetal_backend.dto.EquipmentCaseResponseDto;
import com.example.promdetal_backend.entity.EquipmentCase;
import com.example.promdetal_backend.mapper.EquipmentCaseMapper;
import com.example.promdetal_backend.service.EquipmentCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipmentCaseController {

    private final EquipmentCaseService caseService;

    @GetMapping("/api/cases")
    public List<EquipmentCaseResponseDto> all() {
        return caseService.getAll().stream()
                .map(EquipmentCaseMapper::toDto)
                .toList();
    }

    @GetMapping("/api/equipment/{equipmentId}/cases")
    public List<EquipmentCaseResponseDto> list(@PathVariable Long equipmentId) {
        return caseService.getByEquipment(equipmentId).stream()
                .map(EquipmentCaseMapper::toDto)
                .toList();
    }

    @GetMapping("/api/cases/{id}")
    public EquipmentCaseResponseDto get(@PathVariable Long id) {
        return EquipmentCaseMapper.toDto(caseService.get(id));
    }

    @PostMapping("/api/equipment/{equipmentId}/cases")
    public EquipmentCaseResponseDto create(
            @PathVariable Long equipmentId,
            @RequestBody EquipmentCaseRequestDto dto
    ) {
        EquipmentCase entity = new EquipmentCase();
        EquipmentCaseMapper.updateEntity(entity, dto);

        return EquipmentCaseMapper.toDto(
                caseService.create(equipmentId, entity)
        );
    }

    @PutMapping("/api/cases/{id}")
    public EquipmentCaseResponseDto update(
            @PathVariable Long id,
            @RequestBody EquipmentCaseRequestDto dto
    ) {
        EquipmentCase entity = caseService.get(id);
        EquipmentCaseMapper.updateEntity(entity, dto);

        return EquipmentCaseMapper.toDto(
                caseService.update(id, entity)
        );
    }

    @DeleteMapping("/api/cases/{id}")
    public void delete(@PathVariable Long id) {
        caseService.delete(id);
    }
}

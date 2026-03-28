package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.dto.EquipmentCaseRequestDto;
import com.example.promdetal_backend.dto.EquipmentCaseResponseDto;
import com.example.promdetal_backend.entity.EquipmentCase;
import com.example.promdetal_backend.mapper.EquipmentCaseMapper;
import com.example.promdetal_backend.service.EquipmentCaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EquipmentCaseController {

    private final EquipmentCaseService caseService;

    // Все кейсы
    @GetMapping("/cases")
    public List<EquipmentCaseResponseDto> all() {
        return caseService.getAllCases(); // ✅ возвращает DTO напрямую
    }

    // Кейсы по группе
    @GetMapping("/groups/{groupId}/cases")
    public List<EquipmentCaseResponseDto> listByGroup(@PathVariable Long groupId) {
        return caseService.getByGroup(groupId); // ✅ возвращает DTO напрямую
    }

    // Один кейс
    @GetMapping("/cases/{id}")
    public EquipmentCaseResponseDto get(@PathVariable Long id) {
        return caseService.getCase(id);
    }

    // Создание кейса
    @PostMapping("/groups/{groupId}/cases")
    public EquipmentCaseResponseDto create(
            @PathVariable Long groupId,
            @Valid @RequestBody EquipmentCaseRequestDto dto
    ) {
        return caseService.createCase(groupId, dto); // ✅ сервис сам мапит и сохраняет
    }

    // Обновление кейса
    @PutMapping("/cases/{id}")
    public EquipmentCaseResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody EquipmentCaseRequestDto dto
    ) {
        return caseService.updateCase(id, dto);
    }

    // Удаление
    @DeleteMapping("/cases/{id}")
    public void delete(@PathVariable Long id) {
        caseService.deleteCase(id);
    }
}

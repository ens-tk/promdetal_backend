package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.entity.EquipmentCase;
import com.example.promdetal_backend.service.EquipmentCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment/{equipmentId}/cases")
@RequiredArgsConstructor
public class EquipmentCaseController {

    private final EquipmentCaseService caseService;

    @GetMapping
    public List<EquipmentCase> list(@PathVariable Long equipmentId) {
        return caseService.getByEquipment(equipmentId);
    }

    @GetMapping("/{caseId}")
    public EquipmentCase get(@PathVariable Long caseId) {
        return caseService.get(caseId);
    }

    @PostMapping
    public EquipmentCase create(
            @PathVariable Long equipmentId,
            @RequestBody EquipmentCase c
    ) {
        return caseService.create(equipmentId, c);
    }

    @PutMapping("/{caseId}")
    public EquipmentCase update(
            @PathVariable Long caseId,
            @RequestBody EquipmentCase c
    ) {
        return caseService.update(caseId, c);
    }

    @DeleteMapping("/{caseId}")
    public void delete(@PathVariable Long caseId) {
        caseService.delete(caseId);
    }
}


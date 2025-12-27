package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.dto.EquipmentDto;
import com.example.promdetal_backend.entity.Equipment;
import com.example.promdetal_backend.entity.FileInfo;
import com.example.promdetal_backend.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Operation(summary = "Get all equipment (optional by group)")
    @GetMapping
    public List<EquipmentDto> getAll(@RequestParam(required = false) Long groupId) {
        return equipmentService.getAll(groupId);
    }

    @Operation(summary = "Get equipment by slug")
    @GetMapping("/{slug}")
    public EquipmentDto getBySlug(@PathVariable String slug) {
        return equipmentService.getBySlug(slug);
    }

    @Operation(summary = "Create equipment")
    @PostMapping
    public EquipmentDto create(@RequestBody EquipmentDto dto) {
        return equipmentService.create(dto);
    }

    @Operation(summary = "Update equipment")
    @PutMapping("/{id}")
    public EquipmentDto update(@PathVariable Long id, @RequestBody EquipmentDto dto) {
        return equipmentService.update(id, dto);
    }

    @Operation(summary = "Delete equipment")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add image to equipment")
    @PostMapping("/{id}/images/{fileId}")
    public EquipmentDto addImage(@PathVariable Long id, @PathVariable UUID fileId) {
        return equipmentService.addImage(id, fileId);
    }

    @Operation(summary = "Get equipment for main page")
    @GetMapping("/main")
    public List<EquipmentDto> getMainEquipment() {
        return equipmentService.getMainEquipment();
    }
}

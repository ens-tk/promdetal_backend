package com.example.promdetal_backend.controller;

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
@RequestMapping("/api/Equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Operation(summary = "Get all equipment")
    @GetMapping
    public List<Equipment> getAll() {
        return equipmentService.getAll();
    }

    @Operation(summary = "Get equipment by slug")
    @GetMapping("/{slug}")
    public Equipment getBySlug(@PathVariable String slug) {
        return equipmentService.getBySlug(slug);
    }

    @Operation(summary = "Create new equipment")
    @PostMapping
    public Equipment create(@RequestBody Equipment equipment) {
        return equipmentService.create(equipment);
    }

    @Operation(summary = "Update equipment")
    @PutMapping("/{id}")
    public Equipment update(@PathVariable Long id, @RequestBody Equipment equipment) {
        return equipmentService.update(id, equipment);
    }

    @Operation(summary = "Add uploaded image to equipment")
    @PostMapping("/{id}/images/{fileId}")
    public FileInfo addImage(@PathVariable Long id, @PathVariable UUID fileId) {
        return equipmentService.addImage(id, fileId);
    }

    @Operation(summary = "Get equipment visible on main page")
    @GetMapping("/main")
    public List<Equipment> getMainEquipment() {
        return equipmentService.getMainEquipment();
    }

}

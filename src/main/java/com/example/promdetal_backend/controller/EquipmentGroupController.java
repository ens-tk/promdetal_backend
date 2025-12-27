package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.dto.EquipmentGroupCreateDto;
import com.example.promdetal_backend.dto.EquipmentGroupDto;
import com.example.promdetal_backend.service.EquipmentGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class EquipmentGroupController {

    private final EquipmentGroupService groupService;

    @GetMapping
    public List<EquipmentGroupDto> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public EquipmentGroupDto getById(@PathVariable Long id) {
        return groupService.getById(id);
    }

    @PostMapping
    public EquipmentGroupDto create(@RequestBody EquipmentGroupCreateDto dto) {
        return groupService.create(dto);
    }

    @PutMapping("/{id}")
    public EquipmentGroupDto update(@PathVariable Long id, @RequestBody EquipmentGroupCreateDto dto) {
        return groupService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


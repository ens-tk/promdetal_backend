package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.dto.HotspotDto;
import com.example.promdetal_backend.entity.Hotspot;
import com.example.promdetal_backend.service.HotspotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/equipment/{equipmentId}/hotspots")
@RequiredArgsConstructor
public class HotspotController {

    private final HotspotService hotspotService;

    @GetMapping
    public List<Hotspot> get(@PathVariable Long equipmentId) {
        return hotspotService.getByEquipment(equipmentId);
    }

    @PostMapping
    public Hotspot add(
            @PathVariable Long equipmentId,
            @RequestBody HotspotDto dto
    ) {
        return hotspotService.addHotspot(
                equipmentId, dto.getX(), dto.getY(), dto.getText()
        );
    }

    @PutMapping("/{id}")
    public Hotspot update(
            @PathVariable Long id,
            @RequestBody HotspotDto dto
    ) {
        return hotspotService.update(id, dto.getX(), dto.getY(), dto.getText());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        hotspotService.delete(id);
    }
}

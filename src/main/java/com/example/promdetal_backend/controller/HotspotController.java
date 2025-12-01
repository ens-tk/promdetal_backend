package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.entity.Hotspot;
import com.example.promdetal_backend.service.HotspotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Hotspots")
@RequiredArgsConstructor
public class HotspotController {

    private final HotspotService hotspotService;

    @PostMapping
    public Hotspot addHotspot(
            @RequestParam UUID fileId,
            @RequestParam double x,
            @RequestParam double y,
            @RequestParam String text
    ) {
        return hotspotService.addHotspot(fileId, x, y, text);
    }

    @GetMapping("/file/{fileId}")
    public List<Hotspot> getHotspots(@PathVariable UUID fileId) {
        return hotspotService.getHotspotsByFile(fileId);
    }

    @PutMapping("/{id}")
    public Hotspot updateHotspot(
            @PathVariable Long id,
            @RequestParam double x,
            @RequestParam double y,
            @RequestParam String text
    ) {
        return hotspotService.updateHotspot(id, x, y, text);
    }

    @DeleteMapping("/{id}")
    public void deleteHotspot(@PathVariable Long id) {
        hotspotService.deleteHotspot(id);
    }

}

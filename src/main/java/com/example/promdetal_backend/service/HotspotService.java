package com.example.promdetal_backend.service;

import com.example.promdetal_backend.entity.FileInfo;
import com.example.promdetal_backend.entity.Hotspot;
import com.example.promdetal_backend.repository.FileInfoRepository;
import com.example.promdetal_backend.repository.HotspotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotspotService {

    private final HotspotRepository hotspotRepository;
    private final FileInfoRepository fileInfoRepository;

    public Hotspot addHotspot(UUID fileId, double x, double y, String text) {
        FileInfo file = fileInfoRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));

        Hotspot hotspot = new Hotspot();
        hotspot.setFile(file);
        hotspot.setX(x);
        hotspot.setY(y);
        hotspot.setText(text);

        hotspotRepository.save(hotspot);

        file.getHotspots().add(hotspot);
        fileInfoRepository.save(file);

        return hotspot;
    }

    public List<Hotspot> getHotspotsByFile(UUID fileId) {
        FileInfo file = fileInfoRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));
        return file.getHotspots();
    }

    public Hotspot updateHotspot(Long hotspotId, double x, double y, String text) {
        Hotspot hotspot = hotspotRepository.findById(hotspotId)
                .orElseThrow(() -> new RuntimeException("Hotspot not found"));
        hotspot.setX(x);
        hotspot.setY(y);
        hotspot.setText(text);
        return hotspotRepository.save(hotspot);
    }

    public void deleteHotspot(Long hotspotId) {
        hotspotRepository.deleteById(hotspotId);
    }
}

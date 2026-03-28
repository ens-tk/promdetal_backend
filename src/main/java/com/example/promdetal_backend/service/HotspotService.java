package com.example.promdetal_backend.service;

import com.example.promdetal_backend.entity.Equipment;
import com.example.promdetal_backend.entity.FileInfo;
import com.example.promdetal_backend.entity.Hotspot;
import com.example.promdetal_backend.repository.EquipmentRepository;
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
    private final EquipmentRepository equipmentRepository;

    /** Добавить один hotspot */
    public Hotspot addHotspot(Long equipmentId, double x, double y, String text) {

        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        Hotspot hotspot = new Hotspot();
        hotspot.setEquipment(equipment);
        hotspot.setX(x);
        hotspot.setY(y);
        hotspot.setText(text);

        return hotspotRepository.save(hotspot);
    }

    /** Получить все hotspots оборудования */
    public List<Hotspot> getByEquipment(Long equipmentId) {
        return hotspotRepository.findByEquipmentId(equipmentId);
    }

    /** Обновить hotspot */
    public Hotspot update(Long hotspotId, double x, double y, String text) {
        Hotspot hotspot = hotspotRepository.findById(hotspotId)
                .orElseThrow(() -> new RuntimeException("Hotspot not found"));

        hotspot.setX(x);
        hotspot.setY(y);
        hotspot.setText(text);

        return hotspotRepository.save(hotspot);
    }

    /** Удалить hotspot */
    public void delete(Long hotspotId) {
        hotspotRepository.deleteById(hotspotId);
    }

    /** Полная перезапись hotspots (🔥 идеально для админки) */
    public void replaceHotspots(Long equipmentId, List<Hotspot> hotspots) {

        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        hotspotRepository.deleteByEquipmentId(equipmentId);

        for (Hotspot hotspot : hotspots) {
            hotspot.setEquipment(equipment);
            hotspotRepository.save(hotspot);
        }
    }
}

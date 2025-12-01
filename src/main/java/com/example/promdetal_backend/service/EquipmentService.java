package com.example.promdetal_backend.service;

import com.example.promdetal_backend.entity.Equipment;
import com.example.promdetal_backend.entity.FileInfo;
import com.example.promdetal_backend.repository.EquipmentRepository;
import com.example.promdetal_backend.repository.FileInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final FileInfoRepository fileInfoRepository;

    // Получить все оборудование
    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }

    // Получить оборудование по slug
    public Equipment getBySlug(String slug) {
        return equipmentRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
    }

    // Создать новое оборудование
    public Equipment create(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    // Обновить оборудование
    public Equipment update(Long id, Equipment updated) {
        Equipment existing = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        existing.setTitle(updated.getTitle());
        existing.setShortDescription(updated.getShortDescription());
        existing.setFullDescription(updated.getFullDescription());
        existing.setSlug(updated.getSlug());
        existing.setShowOnMain(updated.isShowOnMain()); // обновляем видимость

        return equipmentRepository.save(existing);
    }


    // Привязать загруженный файл к оборудованию
    public FileInfo addImage(Long equipmentId, UUID fileId) {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        FileInfo file = fileInfoRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));

        equipment.getImages().add(file);
        equipmentRepository.save(equipment);

        return file;
    }
    public List<Equipment> getMainEquipment() {
        return equipmentRepository.findByShowOnMainTrue();
    }



}


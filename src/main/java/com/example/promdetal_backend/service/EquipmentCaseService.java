package com.example.promdetal_backend.service;

import com.example.promdetal_backend.entity.Equipment;
import com.example.promdetal_backend.entity.EquipmentCase;
import com.example.promdetal_backend.repository.EquipmentCaseRepository;
import com.example.promdetal_backend.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentCaseService {

    private final EquipmentCaseRepository caseRepository;
    private final EquipmentRepository equipmentRepository;

    public List<EquipmentCase> getByEquipment(Long equipmentId) {
        return caseRepository.findByEquipmentId(equipmentId);
    }

    public List<EquipmentCase> getAll() {
        return caseRepository.findAll();
    }

    public EquipmentCase get(Long id) {
        return caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found"));
    }

    public EquipmentCase create(Long equipmentId, EquipmentCase c) {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        c.setEquipment(equipment);
        return caseRepository.save(c);
    }

    public EquipmentCase update(Long id, EquipmentCase updated) {
        EquipmentCase existing = get(id);
        existing.setCustomer(updated.getCustomer());
        existing.setDeliveryDate(updated.getDeliveryDate());
        existing.setEquipmentType(updated.getEquipmentType());
        existing.setPurpose1(updated.getPurpose1());
        existing.setPurpose2(updated.getPurpose2());
        existing.setPurpose3(updated.getPurpose3());
        existing.setImage(updated.getImage());

        return caseRepository.save(existing);
    }

    public void delete(Long id) {
        caseRepository.deleteById(id);
    }
}

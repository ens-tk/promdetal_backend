package com.example.promdetal_backend.repository;

import com.example.promdetal_backend.entity.EquipmentCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentCaseRepository extends JpaRepository<EquipmentCase, Long> {

    List<EquipmentCase> findByGroupId(Long groupId);
}

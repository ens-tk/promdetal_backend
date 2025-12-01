package com.example.promdetal_backend.repository;

import com.example.promdetal_backend.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findBySlug(String slug);
    List<Equipment> findByShowOnMainTrue();
}

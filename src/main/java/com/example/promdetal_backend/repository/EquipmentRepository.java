package com.example.promdetal_backend.repository;

import com.example.promdetal_backend.entity.Equipment;
import com.example.promdetal_backend.entity.EquipmentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findBySlug(String slug);
    List<Equipment> findByShowOnMainTrue();
    List<Equipment> findByGroup(EquipmentGroup group);

    @Query("SELECT e FROM Equipment e LEFT JOIN e.searchKeywords k " +
            "WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :token, '%')) " +
            "OR LOWER(k) LIKE LOWER(CONCAT('%', :token, '%'))")
    List<Equipment> search(@Param("token") String token);
}

package com.example.promdetal_backend.repository;

import com.example.promdetal_backend.entity.Hotspot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotspotRepository extends JpaRepository<Hotspot, Long> {
    List<Hotspot> findByFileId(UUID fileId);
}

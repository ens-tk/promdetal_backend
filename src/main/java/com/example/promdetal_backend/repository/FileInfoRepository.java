package com.example.promdetal_backend.repository;

import com.example.promdetal_backend.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileInfoRepository extends JpaRepository<FileInfo, UUID> {
}

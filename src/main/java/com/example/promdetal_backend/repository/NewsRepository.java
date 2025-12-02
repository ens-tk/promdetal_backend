package com.example.promdetal_backend.repository;

import com.example.promdetal_backend.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findTop3ByIdNotOrderByCreatedAtDesc(Long excludedId);
}

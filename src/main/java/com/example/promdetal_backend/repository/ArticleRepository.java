package com.example.promdetal_backend.repository;

import com.example.promdetal_backend.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}


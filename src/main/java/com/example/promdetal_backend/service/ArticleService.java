package com.example.promdetal_backend.service;

import com.example.promdetal_backend.entity.Article;
import com.example.promdetal_backend.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Article getById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    public Article create(Article a) {
        return articleRepository.save(a);
    }

    public Article update(Long id, Article updated) {
        Article existing = getById(id);

        existing.setTitle(updated.getTitle());
        existing.setContent(updated.getContent());
        existing.setCoverImage(updated.getCoverImage());
        existing.setRecommended(updated.getRecommended());

        return articleRepository.save(existing);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}

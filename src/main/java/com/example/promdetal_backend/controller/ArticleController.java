package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.dto.ArticleListItemDto;
import com.example.promdetal_backend.entity.Article;
import com.example.promdetal_backend.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public List<ArticleListItemDto> getAll() {
        return articleService.getAll().stream().map(a -> {
            ArticleListItemDto dto = new ArticleListItemDto();
            dto.setId(a.getId());
            dto.setTitle(a.getTitle());
            dto.setCreatedAt(a.getCreatedAt());
            dto.setCoverImage(a.getCoverImage());

            String preview = a.getContent().length() > 100
                    ? a.getContent().substring(0, 100) + "..."
                    : a.getContent();

            dto.setPreview(preview);

            return dto;
        }).toList();
    }

    @GetMapping("/{id}")
    public Article getById(@PathVariable Long id) {
        return articleService.getById(id);
    }

    @PostMapping
    public Article create(@RequestBody Article a) {
        return articleService.create(a);
    }

    @PutMapping("/{id}")
    public Article update(@PathVariable Long id, @RequestBody Article a) {
        return articleService.update(id, a);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        articleService.delete(id);
    }
}

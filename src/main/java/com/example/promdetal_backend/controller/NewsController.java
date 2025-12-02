package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.dto.NewsFullDto;
import com.example.promdetal_backend.dto.NewsListItemDto;
import com.example.promdetal_backend.entity.News;
import com.example.promdetal_backend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public List<NewsListItemDto> getAll() {
        return newsService.getAll();
    }

    @GetMapping("/{id}")
    public NewsFullDto getById(@PathVariable Long id) {
        return newsService.getById(id);
    }

    @PostMapping
    public News create(@RequestBody News n) {
        return newsService.create(n);
    }

    @PutMapping("/{id}")
    public News update(@PathVariable Long id, @RequestBody News n) {
        return newsService.update(id, n);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        newsService.delete(id);
    }
}

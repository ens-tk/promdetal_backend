package com.example.promdetal_backend.service;

import com.example.promdetal_backend.dto.NewsFullDto;
import com.example.promdetal_backend.dto.NewsListItemDto;
import com.example.promdetal_backend.entity.News;
import com.example.promdetal_backend.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public List<NewsListItemDto> getAll() {
        return newsRepository.findAll().stream().map(n -> {
            NewsListItemDto dto = new NewsListItemDto();
            dto.setId(n.getId());
            dto.setTitle(n.getTitle());
            dto.setCreatedAt(n.getCreatedAt());
            dto.setCoverImage(n.getCoverImage());

            String content = n.getContent() != null ? n.getContent() : "";
            String preview = content.length() > 100 ? content.substring(0, 100) + "..." : content;
            dto.setContent(preview);


            dto.setContent(preview);
            return dto;
        }).toList();
    }

    public NewsFullDto getById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        NewsFullDto dto = new NewsFullDto();
        dto.setId(news.getId());
        dto.setTitle(news.getTitle());
        dto.setContent(news.getContent());
        dto.setCreatedAt(news.getCreatedAt());
        dto.setCoverImage(news.getCoverImage());

        List<News> related = newsRepository.findTop3ByIdNotOrderByCreatedAtDesc(id);

        dto.setRelated(
                related.stream().map(n -> {
                    NewsListItemDto item = new NewsListItemDto();
                    item.setId(n.getId());
                    item.setTitle(n.getTitle());
                    item.setCreatedAt(n.getCreatedAt());
                    item.setCoverImage(n.getCoverImage());

                    String relatedContent = n.getContent() != null ? n.getContent() : "";
                    String preview = relatedContent.length() > 100 ? relatedContent.substring(0, 100) + "..." : relatedContent;
                    item.setContent(preview);

                    return item;
                }).toList()
        );

        return dto;
    }

    public News create(News n) {
        return newsRepository.save(n);
    }

    public News update(Long id, News updated) {
        News existing = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        existing.setTitle(updated.getTitle());
        existing.setContent(updated.getContent());
        existing.setCoverImage(updated.getCoverImage());

        return newsRepository.save(existing);
    }

    public void delete(Long id) {
        newsRepository.deleteById(id);
    }
}

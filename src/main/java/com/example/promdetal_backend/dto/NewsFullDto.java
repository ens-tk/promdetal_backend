package com.example.promdetal_backend.dto;

import com.example.promdetal_backend.entity.FileInfo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NewsFullDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private FileInfo coverImage;

    private List<NewsListItemDto> related;
}

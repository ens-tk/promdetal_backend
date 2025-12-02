package com.example.promdetal_backend.dto;

import com.example.promdetal_backend.entity.FileInfo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsListItemDto {
    private Long id;
    private String title;
    private String preview;
    private LocalDateTime createdAt;
    private FileInfo coverImage;
}

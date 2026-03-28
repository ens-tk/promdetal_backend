package com.example.promdetal_backend.service;

import com.example.promdetal_backend.dto.EquipmentDto;
import com.example.promdetal_backend.entity.Equipment;
import com.example.promdetal_backend.repository.EquipmentRepository;
import com.example.promdetal_backend.search.SynonymDictionary;
import com.example.promdetal_backend.search.TextNormalizeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EquipmentSearchService {

    private final EquipmentRepository repository;

    public List<EquipmentDto> search(String query) {
        // 1. Лемматизация и расширение синонимов
        List<String> stems = TextNormalizeUtil.normalize(query);
        Set<String> expanded = SynonymDictionary.expand(stems);

        // 2. Собираем уникальные результаты
        Set<Equipment> result = new HashSet<>();
        for (String token : expanded) {
            result.addAll(repository.search(token)); // репозиторий ищет по названию оборудования
        }

        // 3. Преобразуем в DTO
        return result.stream()
                .map(e -> {
                    EquipmentDto dto = new EquipmentDto();
                    dto.setId(e.getId());
                    dto.setTitle(e.getTitle());
                    dto.setFullDescription(e.getFullDescription());
                    dto.setShortDescription(e.getShortDescription());
                    dto.setSlug(e.getSlug());
                    dto.setShowOnMain(e.isShowOnMain());
                    if (e.getGroup() != null) {
                        dto.setGroupId(e.getGroup().getId());
                    }
                    return dto;
                })
                .toList();
    }

}


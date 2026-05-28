package com.example.promdetal_backend.service;

import com.example.promdetal_backend.dto.ServiceDto;
import com.example.promdetal_backend.entity.ServiceEntity;
import com.example.promdetal_backend.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceEntityService {

    private final ServiceRepository serviceRepository;

    public List<ServiceDto> getAll() {
        return serviceRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public ServiceDto getById(Long id) {
        return toDto(findOrThrow(id));
    }

    public ServiceDto create(ServiceDto dto) {
        ServiceEntity entity = toEntity(new ServiceEntity(), dto);
        return toDto(serviceRepository.save(entity));
    }

    public ServiceDto update(Long id, ServiceDto dto) {
        ServiceEntity existing = findOrThrow(id);
        toEntity(existing, dto);
        return toDto(serviceRepository.save(existing));
    }

    public void delete(Long id) {
        findOrThrow(id);
        serviceRepository.deleteById(id);
    }

    // ── helpers ──────────────────────────────────────────────────────────────

    private ServiceEntity findOrThrow(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found: " + id));
    }

    private ServiceDto toDto(ServiceEntity e) {
        ServiceDto dto = new ServiceDto();
        dto.setId(e.getId());
        dto.setTitle(e.getTitle());
        dto.setDescription(e.getDescription());
        dto.setImageId(e.getImageId());
        dto.setIconId(e.getIconId());
        dto.setFeatures(e.getFeatures());
        return dto;
    }

    private ServiceEntity toEntity(ServiceEntity e, ServiceDto dto) {
        e.setTitle(dto.getTitle());
        e.setDescription(dto.getDescription());
        e.setImageId(dto.getImageId());
        e.setIconId(dto.getIconId());
        e.setFeatures(dto.getFeatures() != null ? dto.getFeatures() : List.of());
        return e;
    }
}

package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.dto.ServiceDto;
import com.example.promdetal_backend.service.ServiceEntityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceEntityService serviceEntityService;

    @GetMapping
    public List<ServiceDto> getAll() {
        return serviceEntityService.getAll();
    }

    @GetMapping("/{id}")
    public ServiceDto getById(@PathVariable Long id) {
        return serviceEntityService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceDto create(@Valid @RequestBody ServiceDto dto) {
        return serviceEntityService.create(dto);
    }

    @PutMapping("/{id}")
    public ServiceDto update(@PathVariable Long id, @Valid @RequestBody ServiceDto dto) {
        return serviceEntityService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        serviceEntityService.delete(id);
    }
}

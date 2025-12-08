package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.entity.Partner;
import com.example.promdetal_backend.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @GetMapping
    public List<Partner> list() {
        return partnerService.getAll();
    }

    @GetMapping("/{id}")
    public Partner get(@PathVariable Long id) {
        return partnerService.get(id);
    }

    @PostMapping
    public Partner create(@RequestBody Partner p) {
        return partnerService.create(p);
    }

    @PutMapping("/{id}")
    public Partner update(@PathVariable Long id, @RequestBody Partner p) {
        return partnerService.update(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        partnerService.delete(id);
    }
}

package com.example.promdetal_backend.service;

import com.example.promdetal_backend.entity.Partner;
import com.example.promdetal_backend.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;

    public List<Partner> getAll() {
        return partnerRepository.findAll();
    }

    public Partner get(Long id) {
        return partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partner not found"));
    }

    public Partner create(Partner p) {
        return partnerRepository.save(p);
    }

    public Partner update(Long id, Partner updated) {
        Partner existing = get(id);

        existing.setName(updated.getName());
        existing.setImage(updated.getImage());

        return partnerRepository.save(existing);
    }

    public void delete(Long id) {
        partnerRepository.deleteById(id);
    }
}
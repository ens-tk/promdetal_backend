package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.dto.ContactFormDto;
import com.example.promdetal_backend.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
@CrossOrigin
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<String> sendContactForm(@RequestBody ContactFormDto form) {
        contactService.sendContactForm(form);
        return ResponseEntity.ok("Сообщение отправлено");
    }
}


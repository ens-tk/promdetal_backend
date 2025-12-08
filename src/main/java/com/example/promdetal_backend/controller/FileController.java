package com.example.promdetal_backend.controller;

import com.example.promdetal_backend.entity.FileInfo;
import com.example.promdetal_backend.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/Files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @Operation(summary = "Upload file")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileInfo> upload(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        return ResponseEntity.ok(fileService.saveFile(file));
    }

    @Operation(summary = "Download file by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Resource> download(@PathVariable UUID id) throws IOException {
        FileInfo info = fileService.getInfo(id);
        Resource file = fileService.getFile(id);

        String extension = info.getExtension().toLowerCase();
        String contentType = switch (extension) {
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "gif" -> "image/gif";
            default -> "application/octet-stream";
        };

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(file);
    }

}

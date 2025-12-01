package com.example.promdetal_backend.service;

import com.example.promdetal_backend.entity.FileInfo;
import com.example.promdetal_backend.repository.FileInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    private final FileInfoRepository fileInfoRepository;

    public FileInfo saveFile(MultipartFile file) throws IOException {

        String originalName = file.getOriginalFilename();
        String extension = "";

        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf(".") + 1);
        }

        File folder = new File(uploadDir).getAbsoluteFile();
        if (!folder.exists() && !folder.mkdirs()) {
            throw new IOException("Cannot create upload directory: " + folder.getAbsolutePath());
        }


        UUID fileUuid = UUID.randomUUID();
        String savedName = fileUuid + "." + extension;

        File savedFile = new File(folder, savedName);
        file.transferTo(savedFile);

        // Создаём сущность без установки id (id сгенерирует JPA)
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(originalName);
        fileInfo.setExtension(extension);
        fileInfo.setSize(file.getSize());
        fileInfo.setPath(savedFile.getAbsolutePath());

        // Сохраняем в БД — Hibernate присвоит id автоматически
        return fileInfoRepository.save(fileInfo);
    }

    public FileInfo getInfo(UUID id) {
        return fileInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    public UrlResource getFile(UUID id) throws IOException {
        FileInfo info = getInfo(id);

        Path path = Paths.get(info.getPath());
        return new UrlResource(path.toUri());
    }
}

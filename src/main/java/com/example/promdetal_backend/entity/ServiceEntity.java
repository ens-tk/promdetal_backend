package com.example.promdetal_backend.entity;

import com.example.promdetal_backend.converter.JsonListConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
@Data
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    /** UUID-строка файла изображения (ссылка без FK) */
    private String imageId;

    /** Slug иконки, например "settings" или "wrench" */
    private String iconId;

    /** Список пунктов «Что включено», хранится как JSON-массив в TEXT-колонке */
    @Convert(converter = JsonListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> features = new ArrayList<>();
}

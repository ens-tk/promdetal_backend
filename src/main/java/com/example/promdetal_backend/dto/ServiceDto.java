package com.example.promdetal_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ServiceDto {

    private Long id;

    @NotBlank
    private String title;

    private String description;

    private String imageId;

    private String iconId;

    private List<String> features = new ArrayList<>();
}

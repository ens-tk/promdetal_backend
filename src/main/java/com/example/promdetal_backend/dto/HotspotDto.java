package com.example.promdetal_backend.dto;

import lombok.Data;

@Data
public class HotspotDto {
    private Long id;
    private double x;
    private double y;
    private String text;
}

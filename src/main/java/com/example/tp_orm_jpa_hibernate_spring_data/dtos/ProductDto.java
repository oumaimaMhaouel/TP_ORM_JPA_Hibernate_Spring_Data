package com.example.tp_orm_jpa_hibernate_spring_data.dtos;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private int quantity;

}

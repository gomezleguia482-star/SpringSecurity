package com.example.SpringSecurity.Dto.Request.RequestProduct;

import lombok.Data;

@Data
public class ProductRequestDto {

    private String name;
    private double price;
    private int stock;
    private String category;
}

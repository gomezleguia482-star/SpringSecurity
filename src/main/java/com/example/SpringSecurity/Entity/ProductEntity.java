package com.example.SpringSecurity.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_product")
    private long id_product;

    @Column (name = "name_Product")
    private String name_product;

    @Column (name = "price_product")
    private double price_product;

    @Column (name = "stock_product")
    private int stock_product;

    @Column (name = "category_product")
    private String category_product;
}

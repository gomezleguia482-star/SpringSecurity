package com.example.SpringSecurity.Services.ServicesProduct;

import com.example.SpringSecurity.Dto.Request.RequestProduct.ProductRequestDto;
import com.example.SpringSecurity.Dto.Response.ResponseProduct.ProductResponseDto;
import com.example.SpringSecurity.Entity.ProductEntity;
import com.example.SpringSecurity.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto){

        try{
            ProductEntity product = ProductEntity.builder()
                    .name_product(productRequestDto.getName())
                    .price_product(productRequestDto.getPrice())
                    .stock_product(productRequestDto.getStock())
                    .category_product(productRequestDto.getCategory())
                    .build();

            productRepository.save(product);

            return ProductResponseDto.builder()
                    .message("Producto agregado correctamente")
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("Error en el registro del producto");
            return null;
        }
    }
}

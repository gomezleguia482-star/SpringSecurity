package com.example.SpringSecurity.Controller.Product;

import com.example.SpringSecurity.Dto.Request.RequestProduct.ProductRequestDto;
import com.example.SpringSecurity.Dto.Response.ResponseProduct.ProductResponseDto;
import com.example.SpringSecurity.Services.ServicesProduct.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/product")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProductController {

    private final ProductService productService;

    @PostMapping ("/add")
    public ProductResponseDto add(@RequestBody ProductRequestDto requestDto){
         return productService.addProduct(requestDto);
    }
}

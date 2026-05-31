package com.example.tp_orm_jpa_hibernate_spring_data.service;

import com.example.tp_orm_jpa_hibernate_spring_data.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProduct();

    ProductDto findById(long id);

    List<ProductDto> findProductByNameContains(String kw);

    Boolean saveProduct(ProductDto productDto);

    Boolean deleteProduct(long id);

    Boolean updateProduct(ProductDto productDto);
}

package com.example.tp_orm_jpa_hibernate_spring_data.service.serviceImpl;

import com.example.tp_orm_jpa_hibernate_spring_data.dtos.ProductDto;
import com.example.tp_orm_jpa_hibernate_spring_data.entities.Product;
import com.example.tp_orm_jpa_hibernate_spring_data.repositories.ProductRepository;
import com.example.tp_orm_jpa_hibernate_spring_data.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;


    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(
                elem -> objectMapper.convertValue(elem, ProductDto.class)).toList();
    }

    @Override
    public ProductDto findById(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(p -> objectMapper.convertValue(p, ProductDto.class)).orElse(null);
    }

    @Override
    public List<ProductDto> findProductByNameContains(String kw) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(kw);
        return products.stream().map(
                p -> objectMapper.convertValue(p, ProductDto.class)
        ).toList();
    }

    @Override
    public Boolean saveProduct(ProductDto productDto) {
        Product product = objectMapper.convertValue(productDto, Product.class);
        productRepository.save(product);
        return true;
    }

    @Override
    public Boolean deleteProduct(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateProduct(ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setName(productDto.getName());
            optionalProduct.get().setPrice(productDto.getPrice());
            optionalProduct.get().setQuantity(productDto.getQuantity());
            productRepository.save(optionalProduct.get());
            return true;
        } else {
            return false;
        }
    }
}

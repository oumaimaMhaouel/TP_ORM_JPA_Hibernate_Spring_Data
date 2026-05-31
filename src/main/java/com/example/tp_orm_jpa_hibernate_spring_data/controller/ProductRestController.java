package com.example.tp_orm_jpa_hibernate_spring_data.controller;

import com.example.tp_orm_jpa_hibernate_spring_data.dtos.ProductDto;
import com.example.tp_orm_jpa_hibernate_spring_data.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("all-product")
    ResponseEntity<List<ProductDto>> getAll() {
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<ProductDto> getAll(@PathVariable("id") long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping("find-product")
    ResponseEntity<List<ProductDto>> getAll(@RequestParam("kw") String kw) {
        return new ResponseEntity<>(productService.findProductByNameContains(kw), HttpStatus.OK);
    }

    @PostMapping("save")
    ResponseEntity<Boolean> save(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.saveProduct(productDto), HttpStatus.OK);
    }

    @PutMapping("update")
    ResponseEntity<Boolean> update(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.updateProduct(productDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Boolean> update(@PathVariable("id") long id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

}

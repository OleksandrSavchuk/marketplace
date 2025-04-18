package com.example.marketplace.controller;

import com.example.marketplace.dto.ProductDto;
import com.example.marketplace.entity.Product;
import com.example.marketplace.mapper.ProductMapper;
import com.example.marketplace.service.ProductService;
import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getAll() {
        List<Product> products = productService.getAll();
        return productMapper.toDto(products);
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return productMapper.toDto(product);
    }

    @PostMapping
    public ProductDto create(@Validated(OnCreate.class) @RequestBody ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product savedProduct = productService.create(product);
        return productMapper.toDto(savedProduct);
    }

    @PutMapping
    public ProductDto update(@Validated(OnUpdate.class) @RequestBody ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product updatedProduct = productService.update(product);
        return productMapper.toDto(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}

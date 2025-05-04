package com.example.marketplace.controller;

import com.example.marketplace.dto.ProductDto;
import com.example.marketplace.entity.Product;
import com.example.marketplace.mapper.ProductMapper;
import com.example.marketplace.service.CategoryService;
import com.example.marketplace.service.ProductService;
import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public ProductDto create(@Validated(OnCreate.class) @RequestBody ProductDto productDto, Principal principal) {
        return productService.create(productDto, principal);
    }

    @PutMapping
    public ProductDto update(@Validated(OnUpdate.class) @RequestBody ProductDto productDto, Principal principal) {
        return productService.update(productDto, principal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Principal principal) {
        productService.delete(id, principal);
    }

}

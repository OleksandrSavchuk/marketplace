package com.example.marketplace.controller;

import com.example.marketplace.dto.ProductDto;
import com.example.marketplace.service.ProductService;
import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Product Controller"
)
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Get list of products")
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by id")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping
    @Operation(summary = "Create product")
    public ProductDto create(@Validated(OnCreate.class) @RequestBody ProductDto productDto, Principal principal) {
        return productService.create(productDto, principal);
    }

    @PreAuthorize("hasRole('SELLER')")
    @PutMapping
    @Operation(summary = "Update product")
    public ProductDto update(@Validated(OnUpdate.class) @RequestBody ProductDto productDto, Principal principal) {
        return productService.update(productDto, principal);
    }

    @PreAuthorize("hasRole('SELLER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product")
    public void delete(@PathVariable Long id, Principal principal) {
        productService.delete(id, principal);
    }

}

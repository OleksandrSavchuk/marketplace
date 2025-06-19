package com.example.marketplace.service;

import com.example.marketplace.dto.ProductDto;
import com.example.marketplace.dto.ProductImageDto;

import java.security.Principal;
import java.util.List;

public interface ProductService {

    List<ProductDto> getAll();

    ProductDto getById(Long id);

    List<ProductDto> getAllProductsBySellerId(Long sellerId);

    ProductDto create(ProductDto product, Principal principal);

    ProductDto update(ProductDto product, Principal principal);

    void delete(Long id, Principal principal);

    void uploadImage(Long id, ProductImageDto productImageDto);

}

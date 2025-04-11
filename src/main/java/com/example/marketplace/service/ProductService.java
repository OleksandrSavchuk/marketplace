package com.example.marketplace.service;

import com.example.marketplace.entity.Product;

import java.util.List;

public interface ProductService {

    Product getById(Long id);

    List<Product> getAllProductsByUserId(Long userId);

    Product create(Product product);

    Product update(Product product);

    void delete(Long id);

}

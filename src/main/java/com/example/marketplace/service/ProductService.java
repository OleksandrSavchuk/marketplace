package com.example.marketplace.service;

import com.example.marketplace.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product getById(Long id);

    List<Product> getAllProductsBySellerId(Long sellerId);

    Product create(Product product);

    Product update(Product product);

    void delete(Long id);

}

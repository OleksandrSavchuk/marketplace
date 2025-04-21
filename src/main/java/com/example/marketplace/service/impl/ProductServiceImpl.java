package com.example.marketplace.service.impl;

import com.example.marketplace.entity.Product;
import com.example.marketplace.repository.ProductRepository;
import com.example.marketplace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {

        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProductsBySellerId(Long sellerId) {

        return productRepository.findBySellerId(sellerId);
    }

    @Override
    public Product create(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {

        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {

        productRepository.deleteById(id);
    }

}

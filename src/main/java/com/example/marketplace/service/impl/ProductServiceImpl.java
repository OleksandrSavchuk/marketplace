package com.example.marketplace.service.impl;

import com.example.marketplace.dto.ProductDto;
import com.example.marketplace.dto.ProductImageDto;
import com.example.marketplace.entity.Category;
import com.example.marketplace.entity.Product;
import com.example.marketplace.entity.ProductImage;
import com.example.marketplace.entity.User;
import com.example.marketplace.exception.AccessDeniedException;
import com.example.marketplace.mapper.ProductImageMapper;
import com.example.marketplace.mapper.ProductMapper;
import com.example.marketplace.repository.ProductRepository;
import com.example.marketplace.service.CategoryService;
import com.example.marketplace.service.ImageService;
import com.example.marketplace.service.ProductService;
import com.example.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final UserService userService;

    private final ImageService imageService;

    private final ProductMapper productMapper;

    private final ProductImageMapper productImageMapper;


    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDto(products);
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAllProductsBySellerId(Long sellerId) {
        List<Product> products = productRepository.findBySellerId(sellerId);
        return productMapper.toDto(products);
    }

    @Override
    public ProductDto create(ProductDto productDto, Principal principal) {
        User seller = userService.getByUsername(principal.getName());
        Product product = productMapper.toEntity(productDto);
        Category category = categoryService.getById(productDto.getCategoryId());
        product.setSeller(seller);
        product.setCategory(category);
        product.setCreatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto update(ProductDto productDto, Principal principal) {
        Product product = getOwnProduct(productDto.getId(), principal);

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setInventoryCount(productDto.getInventoryCount());
        product.setCategory(categoryService.getById(productDto.getCategoryId()));
        Product updatedProduct = productRepository.save(product);

        return productMapper.toDto(updatedProduct);
    }

    @Override
    public void delete(Long id, Principal principal) {
        Product product = getOwnProduct(id, principal);
        productRepository.delete(product);
    }

    private Product getOwnProduct(Long productId, Principal principal) {
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);
        if (!product.getSeller().getUsername().equals(principal.getName())) {
            throw new AccessDeniedException();
        }
        return product;
    }

    @Override
    public void uploadImage(Long id, ProductImageDto productImageDto) {
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        ProductImage image = productImageMapper.toEntity(productImageDto);
        String fileName = imageService.upload(image);
        product.getImages().add(fileName);
        productRepository.save(product);
    }

}

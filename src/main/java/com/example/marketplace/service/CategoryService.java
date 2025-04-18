package com.example.marketplace.service;

import com.example.marketplace.entity.Category;

import java.util.List;

public interface CategoryService {

    Category getById(Long id);

    List<Category> getAllCategories();

    Category create(Category category);

    Category update(Category category);

    void delete(Long id);

}

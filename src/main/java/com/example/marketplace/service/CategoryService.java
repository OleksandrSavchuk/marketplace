package com.example.marketplace.service;

import com.example.marketplace.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getById(Long id);

    Category create(Category category);

    Category update(Category category);

    void delete(Long id);

}

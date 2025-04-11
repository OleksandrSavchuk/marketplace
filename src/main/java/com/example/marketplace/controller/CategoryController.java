package com.example.marketplace.controller;

import com.example.marketplace.dto.CategoryDto;
import com.example.marketplace.entity.Category;
import com.example.marketplace.mapper.CategoryMapper;
import com.example.marketplace.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryService.getAllCategories();
        return categoryMapper.toDto(categories);
    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return categoryMapper.toDto(category);
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id,
                              @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category updatedCategory = categoryService.update(category);
        return categoryMapper.toDto(updatedCategory);
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category createdCategory = categoryService.create(category);
        return categoryMapper.toDto(createdCategory);
    }

}

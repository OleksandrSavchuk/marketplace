package com.example.marketplace.controller;

import com.example.marketplace.dto.CategoryDto;
import com.example.marketplace.entity.Category;
import com.example.marketplace.mapper.CategoryMapper;
import com.example.marketplace.service.CategoryService;
import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Validated
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

    @PutMapping
    public CategoryDto update(@Validated(OnUpdate.class) @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category updatedCategory = categoryService.update(category);
        return categoryMapper.toDto(updatedCategory);
    }

    @PostMapping
    public CategoryDto create(@Validated(OnCreate.class) @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category createdCategory = categoryService.create(category);
        return categoryMapper.toDto(createdCategory);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

}

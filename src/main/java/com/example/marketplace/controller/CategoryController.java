package com.example.marketplace.controller;

import com.example.marketplace.dto.CategoryDto;
import com.example.marketplace.entity.Category;
import com.example.marketplace.mapper.CategoryMapper;
import com.example.marketplace.service.CategoryService;
import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Category Controller",
        description = "Category API"
)
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    @Operation(summary = "Get list of categories")
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryService.getAll();
        return categoryMapper.toDto(categories);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id")
    public CategoryDto getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return categoryMapper.toDto(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    @Operation(summary = "Update category")
    public CategoryDto update(@Validated(OnUpdate.class) @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category updatedCategory = categoryService.update(category);
        return categoryMapper.toDto(updatedCategory);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Create category")
    public CategoryDto create(@Validated(OnCreate.class) @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category createdCategory = categoryService.create(category);
        return categoryMapper.toDto(createdCategory);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

}

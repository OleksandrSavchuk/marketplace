package com.example.marketplace.mapper;

import com.example.marketplace.dto.CategoryDto;
import com.example.marketplace.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends Mappable<Category, CategoryDto> {
}

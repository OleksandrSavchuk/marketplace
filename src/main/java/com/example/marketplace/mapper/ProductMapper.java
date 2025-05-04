package com.example.marketplace.mapper;

import com.example.marketplace.dto.ProductDto;
import com.example.marketplace.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends Mappable<Product, ProductDto> {

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

}

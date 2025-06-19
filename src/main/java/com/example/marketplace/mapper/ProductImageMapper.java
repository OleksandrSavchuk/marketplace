package com.example.marketplace.mapper;

import com.example.marketplace.dto.ProductImageDto;
import com.example.marketplace.entity.ProductImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductImageMapper extends Mappable<ProductImage, ProductImageDto>{
}

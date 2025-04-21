package com.example.marketplace.mapper;

import com.example.marketplace.dto.CartDto;
import com.example.marketplace.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper extends Mappable<Cart, CartDto> {
}

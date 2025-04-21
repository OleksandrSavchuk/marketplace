package com.example.marketplace.mapper;

import com.example.marketplace.dto.CartItemDto;
import com.example.marketplace.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper extends Mappable<CartItem, CartItemDto> {
}

package com.example.marketplace.mapper;

import com.example.marketplace.dto.CartItemDto;
import com.example.marketplace.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper extends Mappable<CartItem, CartItemDto> {

    @Mapping(source = "product.id", target = "productId")
    CartItemDto toDto(CartItem cartItem);

}

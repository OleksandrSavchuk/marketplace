package com.example.marketplace.service;

import com.example.marketplace.dto.CartItemDto;

import java.security.Principal;
import java.util.List;

public interface CartService {

    List<CartItemDto> getCartItemsByCartId(Principal principal);

    CartItemDto addProductToCart(CartItemDto cartItemDto, Principal principal);

    void deleteCartItem(Long id, Principal principal);

}

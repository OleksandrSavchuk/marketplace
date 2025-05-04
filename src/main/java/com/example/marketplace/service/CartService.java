package com.example.marketplace.service;

import com.example.marketplace.dto.CartItemDto;
import com.example.marketplace.entity.Cart;
import com.example.marketplace.entity.CartItem;

import java.security.Principal;
import java.util.List;

public interface CartService {

    List<CartItemDto> getCartItemsByCartId(Principal principal);

    CartItemDto addProductToCart(CartItemDto cartItemDto, Principal principal);

    CartItem updateCartItem(CartItem cartItem);

    void deleteCartItem(Long id, Principal principal);

}

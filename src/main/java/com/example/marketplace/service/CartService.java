package com.example.marketplace.service;

import com.example.marketplace.entity.Cart;
import com.example.marketplace.entity.CartItem;

import java.util.List;

public interface CartService {

    List<CartItem> getCartItemsByCartId(Long cartId);

    CartItem addProductToCart(Long cartId, Long productId, int quantity);

    CartItem updateCartItem(CartItem cartItem);

    void deleteCartItem(Long id);

}

package com.example.marketplace.service.impl;

import com.example.marketplace.entity.Cart;
import com.example.marketplace.entity.CartItem;
import com.example.marketplace.entity.Product;
import com.example.marketplace.repository.CartItemRepository;
import com.example.marketplace.repository.CartRepository;
import com.example.marketplace.repository.ProductRepository;
import com.example.marketplace.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getCartItemsByCartId(Long cartId) {
        return cartItemRepository.findAllByCartId(cartId);
    }

    @Override
    public CartItem addProductToCart(Long cartId, Long productId, int quantity) {

        Cart cart = cartRepository.findById(cartId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        CartItem item = cartItemRepository.findByCartAndProduct(cart, product);

        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(quantity);
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {

        CartItem item = cartItemRepository.findById(cartItem.getId()).orElseThrow(() -> new RuntimeException("Cart not found"));
        if (cartItem.getQuantity() <= 0) {
            cartItemRepository.delete(cartItem);
            return null;
        }
        item.setQuantity(cartItem.getQuantity());

        return cartItemRepository.save(item);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

}

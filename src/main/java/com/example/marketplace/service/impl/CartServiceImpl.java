package com.example.marketplace.service.impl;

import com.example.marketplace.dto.CartItemDto;
import com.example.marketplace.entity.Cart;
import com.example.marketplace.entity.CartItem;
import com.example.marketplace.entity.Product;
import com.example.marketplace.entity.User;
import com.example.marketplace.mapper.CartItemMapper;
import com.example.marketplace.repository.CartItemRepository;
import com.example.marketplace.repository.CartRepository;
import com.example.marketplace.repository.ProductRepository;
import com.example.marketplace.service.CartService;
import com.example.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final CartItemRepository cartItemRepository;

    private final UserService userService;

    private final CartItemMapper cartItemMapper;

    @Override
    public List<CartItemDto> getCartItemsByCartId(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        Cart cart = cartRepository.findByUserId(user.getId());
        List<CartItem> items = cart.getItems();
        return cartItemMapper.toDto(items);
    }

    @Override
    public CartItemDto addProductToCart(CartItemDto cartItemDto, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        Cart cart = cartRepository.findByUserId(user.getId());
        Product product = productRepository.findById(cartItemDto.getProductId()).orElse(null);
        double price = product.getPrice() * cartItemDto.getQuantity();
        CartItem cartItem = cartItemMapper.toEntity(cartItemDto);
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setPrice(price);
        CartItem cartItemSaved = cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(cartItemSaved);
    }

    @Override
    public void deleteCartItem(Long itemId, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        Cart cart = cartRepository.findByUserId(user.getId());
        CartItem cartItem = cartItemRepository.findById(itemId).orElse(null);
        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Cart is not your!");
        }
        cartItemRepository.deleteById(itemId);
    }

}

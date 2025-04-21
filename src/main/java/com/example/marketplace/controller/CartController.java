package com.example.marketplace.controller;

import com.example.marketplace.dto.CartItemDto;
import com.example.marketplace.entity.CartItem;
import com.example.marketplace.mapper.CartItemMapper;
import com.example.marketplace.service.CartService;
import com.example.marketplace.validation.OnCreate;
import com.example.marketplace.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@Validated
public class CartController {

    private final CartService cartService;

    private final CartItemMapper cartItemMapper;

    @GetMapping("/{id}")
    public List<CartItemDto> getCartItemsByCartId(@PathVariable Long id) {
        List<CartItem> items = cartService.getCartItemsByCartId(id);
        return cartItemMapper.toDto(items);
    }

    @PostMapping("/add")
    public CartItemDto addProductToCart(@Validated(OnCreate.class) @RequestBody CartItemDto cartItemDto) {
        CartItem cartItem = cartService.addProductToCart(
                cartItemDto.getCartId(),
                cartItemDto.getProductId(),
                cartItemDto.getQuantity()
        );
        return cartItemMapper.toDto(cartItem);
    }

    @PutMapping("/update")
    public CartItemDto updateCartItem(@Validated(OnUpdate.class) @RequestBody CartItemDto cartItemDto) {
        CartItem cartItem = cartItemMapper.toEntity(cartItemDto);
        CartItem updatedItem = cartService.updateCartItem(cartItem);
        return cartItemMapper.toDto(updatedItem);
    }

    @DeleteMapping("/item/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartService.deleteCartItem(id);
    }

}

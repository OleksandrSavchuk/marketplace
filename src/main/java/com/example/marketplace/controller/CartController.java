package com.example.marketplace.controller;

import com.example.marketplace.dto.CartDto;
import com.example.marketplace.entity.Cart;
import com.example.marketplace.mapper.CartMapper;
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

    private final CartMapper cartMapper;

    @GetMapping
    public List<CartDto> getAll() {
        List<Cart> carts = cartService.getAll();
        return cartMapper.toDto(carts);
    }

    @GetMapping("/{id}")
    public CartDto getById(@PathVariable Long id) {
        Cart cart = cartService.getById(id);
        return cartMapper.toDto(cart);
    }

    @PostMapping
    public CartDto create(@Validated(OnCreate.class) @RequestBody CartDto cartDto) {
        Cart cart = cartMapper.toEntity(cartDto);
        Cart createdCart = cartService.create(cart);
        return cartMapper.toDto(createdCart);
    }

    @PutMapping
    public CartDto update(@Validated(OnUpdate.class) @RequestBody CartDto cartDto) {
        Cart cart = cartMapper.toEntity(cartDto);
        Cart updatedCart = cartService.update(cart);
        return cartMapper.toDto(updatedCart);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cartService.delete(id);
    }

}

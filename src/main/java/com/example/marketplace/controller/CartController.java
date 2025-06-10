package com.example.marketplace.controller;

import com.example.marketplace.dto.CartItemDto;
import com.example.marketplace.service.CartService;
import com.example.marketplace.validation.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Cart Controller",
        description = "Cart API"
)
public class CartController {

    private final CartService cartService;

    @GetMapping
    @Operation(summary = "Get list of CartItem by cart id")
    public List<CartItemDto> getCartItemsByCartId(Principal principal) {
        return cartService.getCartItemsByCartId(principal);
    }

    @PostMapping("/add")
    @Operation(summary = "Add product to cart")
    public CartItemDto addProductToCart(@Validated(OnCreate.class) @RequestBody CartItemDto cartItemDto, Principal principal) {
        return cartService.addProductToCart(cartItemDto, principal);
    }

    @DeleteMapping("/item/{itemId}")
    @Operation(summary = "Delete CartItem")
    public void deleteCartItem(@PathVariable Long itemId, Principal principal) {
        cartService.deleteCartItem(itemId, principal);
    }

}

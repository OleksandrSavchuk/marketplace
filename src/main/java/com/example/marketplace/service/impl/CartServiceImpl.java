package com.example.marketplace.service.impl;

import com.example.marketplace.entity.Cart;
import com.example.marketplace.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    @Override
    public Cart getById(Long id) {
        return null;
    }

    @Override
    public List<Cart> getAll() {
        return List.of();
    }

    @Override
    public Cart create(Cart cart) {
        return null;
    }

    @Override
    public Cart update(Cart cart) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

}

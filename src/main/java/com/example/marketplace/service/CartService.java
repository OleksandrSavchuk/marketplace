package com.example.marketplace.service;

import com.example.marketplace.entity.Cart;

import java.util.List;

public interface CartService {

    Cart getById(Long id);

    List<Cart> getAll();

    Cart create(Cart cart);

    Cart update(Cart cart);

    void delete(Long id);

}

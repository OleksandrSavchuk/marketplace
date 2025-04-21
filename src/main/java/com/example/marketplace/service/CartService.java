package com.example.marketplace.service;

import com.example.marketplace.entity.Cart;

import java.util.List;

public interface CartService {

    List<Cart> getAll();

    Cart getById(Long id);

    Cart create(Cart cart);

    Cart update(Cart cart);

    void delete(Long id);

}

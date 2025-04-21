package com.example.marketplace.service.impl;

import com.example.marketplace.entity.Cart;
import com.example.marketplace.repository.CartRepository;
import com.example.marketplace.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<Cart> getAll() {

        return cartRepository.findAll();
    }

    @Override
    public Cart getById(Long id) {

        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart create(Cart cart) {

        return cartRepository.save(cart);
    }

    @Override
    public Cart update(Cart cart) {

        return cartRepository.save(cart);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

}

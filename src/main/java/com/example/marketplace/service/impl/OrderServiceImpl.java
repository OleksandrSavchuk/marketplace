package com.example.marketplace.service.impl;

import com.example.marketplace.entity.*;
import com.example.marketplace.entity.enums.OrderStatus;
import com.example.marketplace.exception.ResourceNotFoundException;
import com.example.marketplace.repository.*;
import com.example.marketplace.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Order createFromCart(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(ResourceNotFoundException::new);

        Cart cart = cartRepository.findByUserId(user.getId());

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        for (CartItem item : cart.getItems()) {
            if (item.getQuantity() > item.getProduct().getInventoryCount()) {
                throw new IllegalStateException("Not enough inventory for product: " + item.getProduct().getTitle());
            }
        }

        Order order = new Order();
        order.setBuyer(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PROCESSING);
        order.setTotalPrice(0.00);
        orderRepository.save(order);

        double totalPrice = 0.00;

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtPurchase(cartItem.getPrice());

            orderItemRepository.save(orderItem);

            Product product = cartItem.getProduct();
            product.setInventoryCount(product.getInventoryCount() - cartItem.getQuantity());
            productRepository.save(product);

            totalPrice += cartItem.getQuantity() * cartItem.getPrice();
        }

        order.setTotalPrice(totalPrice);

        cart.getItems().clear();
        cartRepository.save(cart);

        return orderRepository.save(order);
    }

    @Override
    public Order update(Order product) {
        return orderRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}

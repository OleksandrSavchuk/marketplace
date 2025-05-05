package com.example.marketplace.service.impl;

import com.example.marketplace.dto.OrderDto;
import com.example.marketplace.entity.*;
import com.example.marketplace.entity.enums.OrderStatus;
import com.example.marketplace.entity.enums.Role;
import com.example.marketplace.exception.ResourceNotFoundException;
import com.example.marketplace.mapper.OrderMapper;
import com.example.marketplace.repository.*;
import com.example.marketplace.service.OrderService;
import com.example.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
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
    private final OrderMapper orderMapper;
    private final UserService userService;

    @Override
    public List<OrderDto> getAll(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        List<Order> orders = switch (user.getRole()) {
            case ROLE_USER -> orderRepository.findByBuyer(user);
            case ROLE_SELLER -> orderRepository.findBySeller(user);
            case ROLE_ADMIN -> orderRepository.findAll();
        };
        return orderMapper.toDto(orders);
    }

    @Override
    public OrderDto getById(Long id, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        Order order = orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        boolean isBuyer = order.getBuyer().getId().equals(user.getId());
        boolean isSeller = order.getSeller().getId().equals(user.getId());

        if (user.getRole() == Role.ROLE_USER && !isBuyer) {
            throw new AccessDeniedException("You can only view your own orders.");
        }
        if (user.getRole() == Role.ROLE_SELLER && !isSeller) {
            throw new AccessDeniedException("You can only view your own orders.");
        }

        return orderMapper.toDto(order);
    }

    @Transactional
    @Override
    public OrderDto createFromCart(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
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

        return orderMapper.toDto(order);
    }

    @Override
    public void delete(Long id, Principal principal) {
        orderRepository.deleteById(id);
    }
}

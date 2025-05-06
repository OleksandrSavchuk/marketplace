package com.example.marketplace.service.impl;

import com.example.marketplace.dto.UserDto;
import com.example.marketplace.entity.Cart;
import com.example.marketplace.entity.User;
import com.example.marketplace.entity.enums.MailType;
import com.example.marketplace.entity.enums.Role;
import com.example.marketplace.mapper.UserMapper;
import com.example.marketplace.repository.CartRepository;
import com.example.marketplace.repository.UserRepository;
import com.example.marketplace.service.MailService;
import com.example.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final CartRepository cartRepository;

    private final UserMapper userMapper;

    private final MailService mailService;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByUsername(String username) {

        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        if (!Set.of("USER", "SELLER").contains(userDto.getRole().toUpperCase())) {
            throw new IllegalStateException("Role not allowed.");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists.");
        }
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            throw new IllegalStateException("Passwords do not match.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String userRole = "ROLE_" + userDto.getRole().toUpperCase();
        Role role = Role.valueOf(userRole);
        user.setRole(role);
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        mailService.sendMail(user, MailType.REGISTRATION, new Properties());

        if (role == Role.ROLE_USER) {
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        return userMapper.toDto(savedUser);
    }

    @Override
    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }



    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}

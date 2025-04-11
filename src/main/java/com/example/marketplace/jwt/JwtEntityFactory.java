package com.example.marketplace.jwt;

import com.example.marketplace.entity.User;

public class JwtEntityFactory {

    public static JwtEntity create(User user) {
        return new JwtEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }

}

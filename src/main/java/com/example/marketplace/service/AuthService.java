package com.example.marketplace.service;

import com.example.marketplace.jwt.JwtRequest;
import com.example.marketplace.jwt.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);

}

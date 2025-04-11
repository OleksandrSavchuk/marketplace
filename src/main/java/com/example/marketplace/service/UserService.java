package com.example.marketplace.service;

import com.example.marketplace.entity.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String username);

    User create(User user);

    User update(User user);

    void delete(Long id);

}

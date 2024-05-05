package com.example.internetshopcar.services;

import com.example.internetshopcar.entities.User;

import java.util.List;

public interface UserService {

    public void registerUser(User user);

    User findUserByEmail(String email);

    List<User> getAllUsers();
}


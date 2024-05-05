package com.example.internetshopcar.services;

import com.example.internetshopcar.entities.User;
import com.example.internetshopcar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;



    @Override
    public void registerUser(User user) {

        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByUserEmail(email);

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }


}

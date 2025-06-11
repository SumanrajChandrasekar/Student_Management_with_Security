package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

    public boolean validateUser(String username, String password) { 
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user != null;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.payload.LoginResponse;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean isValid = userService.validateUser(user.getUsername(), user.getPassword()); // Remove Role

        if (isValid) {
            User loggedInUser = userService.findUserByUsername(user.getUsername()); // Get the user to retrieve the role
            return ResponseEntity.ok(
                new LoginResponse("Login successful", user.getUsername(), loggedInUser.getRole()) // Use the role from the user object
            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Invalid credentials", null, null));
        }
    }
}

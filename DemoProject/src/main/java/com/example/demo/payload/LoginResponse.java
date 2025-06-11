package com.example.demo.payload;

public class LoginResponse {
    private String message;
    private String username;
    private String role;

    public LoginResponse(String message, String username, String role) {
        this.message = message;
        this.username = username;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
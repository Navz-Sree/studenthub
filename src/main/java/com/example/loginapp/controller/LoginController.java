package com.example.loginapp.controller;

import com.example.loginapp.model.User;
import com.example.loginapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userRepository.save(user);

        return "User saved successfully ✅";
    }
    @GetMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password) {

        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
            return "Login Successful ✅";
        } else {
            return "Invalid Username or Password ❌";
        }
    }

    @GetMapping("/test")
    public String test() {
        return "Backend is working 🚀";
    }
}
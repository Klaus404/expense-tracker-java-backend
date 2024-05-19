package com.example.klaus404.expensemanager.controller;

import com.example.klaus404.expensemanager.service.AuthService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


public class FirebaseController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String signUpUser(@RequestParam String email, @RequestParam String password) {
        try {
            authService.signUpUser(email, password);
            return "User signed up successfully.";
        } catch (FirebaseAuthException e) {
            return "Error signing up user: " + e.getMessage();
        }
    }
}

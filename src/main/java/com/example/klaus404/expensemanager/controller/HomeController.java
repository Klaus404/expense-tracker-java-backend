package com.example.klaus404.expensemanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(Principal user){
        return "Welcome " + user.getName() + "!";
    }

}


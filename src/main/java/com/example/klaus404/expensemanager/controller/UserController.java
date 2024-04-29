package com.example.klaus404.expensemanager.controller;

import com.example.klaus404.expensemanager.dto.UserDto;
import com.example.klaus404.expensemanager.exception.NotFoundException;
import com.example.klaus404.expensemanager.model.User;
import com.example.klaus404.expensemanager.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() throws NotFoundException {
        return userService.getUsers();
    }

    @PostMapping("/users")
    public UserDto saveUser(@RequestBody Authentication newUser) throws NotFoundException {
        UserDto userDto = userService.saveUser(newUser);
        if(userDto != null) {   return userDto; }
        else {
            throw new NotFoundException("404: User not found");
        }
    }


}

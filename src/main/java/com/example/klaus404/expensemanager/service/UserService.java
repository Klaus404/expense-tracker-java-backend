package com.example.klaus404.expensemanager.service;

import com.example.klaus404.expensemanager.dao.UserRepository;
import com.example.klaus404.expensemanager.exception.NotFoundException;
import com.example.klaus404.expensemanager.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //Handle the users in the DB
    public void saveUser(Authentication authUser){
        if(!existingUser((UsernamePasswordAuthenticationToken) authUser.getPrincipal())){
            userRepository.save(new User((String) authUser.getPrincipal(), authUser.getPrincipal().hashCode()));
        }
    }

    private boolean existingUser(UsernamePasswordAuthenticationToken p){
        if(userRepository.existsByUsername(p.getName())) return true;
        return false;
    }

    public List<User> getUsers() throws NotFoundException {
        return userRepository.getAllUsers()
                .orElseThrow(() -> new NotFoundException("404: User not found!"));
    }
}

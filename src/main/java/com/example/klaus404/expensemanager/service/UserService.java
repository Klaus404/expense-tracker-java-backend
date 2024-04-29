package com.example.klaus404.expensemanager.service;

import com.example.klaus404.expensemanager.dao.UserRepository;
import com.example.klaus404.expensemanager.dto.UserDto;
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
    public UserDto saveUser(Authentication authUser) throws NotFoundException {
        if(!existingUser((UsernamePasswordAuthenticationToken) authUser.getPrincipal())){
            User user = new User((String) authUser.getPrincipal(), authUser.getPrincipal().hashCode());
            userRepository.save(user);
            return user.toDto();
        }
        throw new NotFoundException("User " + authUser);
    }

    private boolean existingUser(UsernamePasswordAuthenticationToken p){
        return userRepository.existsByUsername(p.getName());
    }

    public List<User> getUsers() throws NotFoundException {
        List<User> userList = userRepository.findAll();
        if(userList != null){
            return userList;
        } else {
            throw new NotFoundException("404: User not found!");
        }

    }


}

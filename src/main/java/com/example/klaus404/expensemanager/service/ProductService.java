package com.example.klaus404.expensemanager.service;

import com.example.klaus404.expensemanager.dao.ProductRepository;
import com.example.klaus404.expensemanager.dao.UserRepository;
import com.example.klaus404.expensemanager.entity.Product;
import com.example.klaus404.expensemanager.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ProductService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getProductsForCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            // Handle unauthenticated case
            return Collections.emptyList();
        }

        String currentPrincipalName = authentication.getName();

        // Retrieve user based on the principal name
        User currentUser = userRepository.findByUsername(currentPrincipalName);

        if (currentUser != null) {
            // Retrieve products associated with the user
            return currentUser.getProducts();
        } else {
            // Handle the case when the user is not found
            return Collections.emptyList();
        }
    }
}

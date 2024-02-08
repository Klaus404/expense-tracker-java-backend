package com.example.klaus404.expensemanager.service;

import com.example.klaus404.expensemanager.dao.ProductRepository;
import com.example.klaus404.expensemanager.dao.UserRepository;
import com.example.klaus404.expensemanager.dto.ProductDto;
import com.example.klaus404.expensemanager.entity.Product;
import com.example.klaus404.expensemanager.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ProductService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public ProductDto saveProduct(Product p) {
        productRepository.save(p);
        return p.toDto();
    }

    public List<ProductDto> getProductsForCurrentUser(Long id) {
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
            return currentUser.getProducts().stream()
                    .filter(product -> product.getId() == id)
                    .map(Product::toDto)
                    .collect(Collectors.toList());

        } else {
            // Handle the case when the user is not found
            return Collections.emptyList();
        }
    }

    public List<ProductDto> getProductsForCurrentUserByProductId(int id) {
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
            return currentUser.getProducts().stream()
                    .map(Product::toDto)
                    .collect(Collectors.toList());
        } else {
            // Handle the case when the user is not found
            return Collections.emptyList();
        }
    }
}

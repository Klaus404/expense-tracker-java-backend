package com.example.klaus404.expensemanager.service;

import com.example.klaus404.expensemanager.dao.ProductRepository;
import com.example.klaus404.expensemanager.dao.UserRepository;
import com.example.klaus404.expensemanager.dto.ProductDto;
import com.example.klaus404.expensemanager.model.Product;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(UserRepository userRepository, ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductDto saveProduct(Product product) {
        //Save the product to the DB
        productRepository.save(product);
        return product.toDto();
    }

    public List<ProductDto> getProductsForCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
/*
        if (currentUser != null) {
            // Retrieve products associated with the user
            return currentUser.getProducts().stream()
                    .map(Product::toDto)
                    .collect(Collectors.toList());

        } else {
            // Handle the case when the user is not found
            return Collections.emptyList();
        }*/
        return null;
    }



}

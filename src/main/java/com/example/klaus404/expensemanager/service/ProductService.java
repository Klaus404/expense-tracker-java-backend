package com.example.klaus404.expensemanager.service;

import com.example.klaus404.expensemanager.dao.ProductRepository;
import com.example.klaus404.expensemanager.dao.UserRepository;
import com.example.klaus404.expensemanager.dto.ProductDto;
import com.example.klaus404.expensemanager.entity.Product;
import com.example.klaus404.expensemanager.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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


    public ProductDto saveProduct(Product product, Authentication user) {
        //Save the product to the DB
        productRepository.save(product);

        //Handle the users int the DB
        if(!existingUser((UsernamePasswordAuthenticationToken) user.getPrincipal())){
            //TODO:: trebuie modificata in baza de date lista de produse
        } else {
            //Save a new user in the DB with his products
            List<Product> productList = new ArrayList<Product>();
            productList.add(product);

            userRepository.save(new User(user.getName(), productList, user.hashCode()));
        }

        return product.toDto();
    }

    public List<ProductDto> getProductsForCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || !existingUser( (UsernamePasswordAuthenticationToken) authentication.getPrincipal())) {
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

    public List<Object> getProductsForCurrentUserByProductId(Long id) {
        //Get authentification
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Handle unauthenticated case
        if (authentication == null || !authentication.isAuthenticated()) {
            return Collections.emptyList();
        }

        String currentPrincipalName = authentication.getName();

        // Retrieve user based on the principal name
        User currentUser = userRepository.findByUsername(currentPrincipalName);

        if (currentUser != null) {
            // Retrieve products with specific id associated with the principal
            return currentUser.getProducts().stream()
                    .filter(product -> product.getId() == id)
                    .map(Product::toDto)
                    .collect(Collectors.toList());
        } else {
            // Handle the case when the user is not found
            return Collections.emptyList();

            //TODO:: log something so you know that a user was not found
        }
    }

    private boolean existingUser(UsernamePasswordAuthenticationToken p){
        if(userRepository.existsByUsername(p.getName())) return true;
        return false;
    }
}

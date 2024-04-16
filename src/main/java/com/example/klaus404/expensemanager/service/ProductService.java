package com.example.klaus404.expensemanager.service;

import com.example.klaus404.expensemanager.dao.ProductRepository;
import com.example.klaus404.expensemanager.dao.UserRepository;
import com.example.klaus404.expensemanager.dto.ProductDto;
import com.example.klaus404.expensemanager.exception.NotFoundException;
import com.example.klaus404.expensemanager.model.Product;
import com.example.klaus404.expensemanager.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService( ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    public ProductDto saveProduct(Product product) {
        //Save the product to the DB
        productRepository.save(product);
        return product.toDto();
    }

    public Product putProduct(Product newProduct, Long id){
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setQuantity(newProduct.getQuantity());
                    product.setValue(newProduct.getValue());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return  productRepository.save(newProduct);
                });
    }

    public List<ProductDto> getProductsForCurrentUser() throws NotFoundException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User currentUser = userRepository.findByUsername(currentPrincipalName)
                .orElseThrow( () -> new NotFoundException("404: User not found!"));

        if(currentUser.getProducts() != null){
            return currentUser.getProducts().stream()
                    .map(Product::toDto)
                    .collect(Collectors.toList());
        } else {
            throw new NotFoundException("404: Products not found!");
        }


    }



    public List<ProductDto> getProductsForCurrentUserByProductId(Long id) {
        return null;
    }
}

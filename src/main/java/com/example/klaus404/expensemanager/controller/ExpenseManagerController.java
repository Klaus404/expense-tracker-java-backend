package com.example.klaus404.expensemanager.controller;


import com.example.klaus404.expensemanager.ProductNotFoundException;
import com.example.klaus404.expensemanager.dao.UserRepository;
import com.example.klaus404.expensemanager.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequestEntityConverter;
import org.springframework.web.bind.annotation.*;
import com.example.klaus404.expensemanager.dao.ProductRepository;

import java.security.Principal;
import java.util.List;


@RestController
public class ExpenseManagerController {
    private ProductRepository repository;


    ExpenseManagerController(ProductRepository repository){
        this.repository = repository;
    }

    //Show all products from the DB
    @GetMapping("/products")
    List<Product> all(Principal user){
        return repository.findProductByUser_Email(user.getName());
    }

    //Add one product into the DB
    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct,
                       Principal user){
       return repository.save(newProduct);
    }

    //Show the product with one specific id
    @GetMapping("/products/{id}")
    Product getOne(@PathVariable Long id,
                   Principal user){
        try {
            return repository.findProductByIdAndUser_Email(user.getName(), id);
        } catch (ProductNotFoundException exc) {
            // Handle the exception if needed, or rethrow it
            throw exc;
        }

    }


    //Replace a product with a specific id
    @PutMapping("/products/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id){

        return repository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setQuantity(newProduct.getQuantity());
                    product.setValue(newProduct.getValue());
                    return repository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return  repository.save(newProduct);
                });

    }

    //Delete a product with a specific id
    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id){
        repository.deleteById(id);
    }

}

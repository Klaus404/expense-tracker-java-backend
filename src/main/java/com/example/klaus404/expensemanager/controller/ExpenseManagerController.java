package com.example.klaus404.expensemanager.controller;


import com.example.klaus404.expensemanager.exception.ProductNotFoundException;
import com.example.klaus404.expensemanager.dao.ProductRepository;
import com.example.klaus404.expensemanager.dao.UserRepository;
import com.example.klaus404.expensemanager.entity.Product;
import com.example.klaus404.expensemanager.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
public class ExpenseManagerController {
    private final ProductService productService;


    //Show all products from the DBgit
    @GetMapping("/products")
    List<Product> all(Principal user) {
        if (user.getName() != null) {
            return productService.getProductsForCurrentUser();
        }
        return null;
    }


    //Add one product into the DB
    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct,
                       Principal user){
        productService.saveProduct(newProduct);

        return newProduct;
    }

    //Show the product with one specific id
    @GetMapping("/products/{id}")
    Product getOne(@PathVariable Long id,
                   Principal user){
        try {
            return  null; // TODO: 1/10/24  ;
        } catch (ProductNotFoundException exc) {
            // Handle the exception if needed, or rethrow it
            throw exc;
        }

    }


    //Replace a product with a specific id
    @PutMapping("/products/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id){
/**
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
**/
        return null;
    }

    //Delete a product with a specific id
    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id){
        //productRepository.deleteById(id);
    }

    ExpenseManagerController(ProductRepository productRepository, UserRepository userRepository, ProductService productService) {
        this.productService = productService;
    }

}

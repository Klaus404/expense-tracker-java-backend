package com.example.klaus404.expensemanager.controller;


import com.example.klaus404.expensemanager.dao.ProductRepository;
import com.example.klaus404.expensemanager.dao.UserRepository;
import com.example.klaus404.expensemanager.dto.ProductDto;
import com.example.klaus404.expensemanager.model.Product;
import com.example.klaus404.expensemanager.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {
    private final ProductService productService;


    //Show all products from the DB
    @GetMapping("/products")
    List<ProductDto> all() {
        return productService.getProductsForCurrentUser();
    }


    //Add one product into the DB
    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct){
        productService.saveProduct(newProduct);
        return newProduct;
    }

    //Show the product with one specific id
    @GetMapping("/products/{id}")
    List<Object> getById(@PathVariable Long id){
        return productService.getProductsForCurrentUserByProductId(id);
    }


    //Replace a product with a specific id
    @PutMapping("/products/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id){

        return productService.putProduct(newProduct, id);
    }

    //Delete a product with a specific id
    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id){
        //productRepository.deleteById(id);
    }

    ProductController(ProductRepository productRepository, UserRepository userRepository, ProductService productService) {
        this.productService = productService;
    }
}

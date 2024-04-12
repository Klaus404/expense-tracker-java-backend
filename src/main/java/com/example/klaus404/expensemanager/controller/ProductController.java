package com.example.klaus404.expensemanager.controller;


import com.example.klaus404.expensemanager.dao.ProductRepository;
import com.example.klaus404.expensemanager.dao.UserRepository;
import com.example.klaus404.expensemanager.dto.ProductDto;
import com.example.klaus404.expensemanager.exception.NotFoundException;
import com.example.klaus404.expensemanager.model.Product;
import com.example.klaus404.expensemanager.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {
    private final ProductService productService;


    //Show all products from the DB
    @GetMapping("/products")
    List<ProductDto> all() throws NotFoundException {
        List<ProductDto> userProducts = productService.getProductsForCurrentUser();

        if(userProducts != null) {
            return  userProducts;
        } else{
            throw new NotFoundException("404: Products not found!");
        }


    }


    //Add one product into the DB
    @PostMapping("/products")
    ResponseEntity<ProductDto> newProduct(@RequestBody Product newProduct){
        ProductDto productDto = productService.saveProduct(newProduct);
        if(productDto != null){
            return ResponseEntity.ok(productDto);
        }
        else{
            return  ResponseEntity.notFound().build();
        }



    }

    //Show the product with one specific id
    @GetMapping("/products/{id}")
    List<ProductDto> getById(@PathVariable Long id) throws NotFoundException{
        List<ProductDto> productsForUser = productService.getProductsForCurrentUserByProductId(id);
        if(productsForUser != null){
            return productsForUser;
        } else {
            throw new NotFoundException("404: Products not found!");
        }

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

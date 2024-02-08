package com.example.klaus404.expensemanager.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String email, Long id){
        super("Could not find the product added by " + email + " with the id: " + id);
    }
}

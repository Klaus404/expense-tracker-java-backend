package com.example.klaus404.expensemanager.dto;

import com.example.klaus404.expensemanager.entity.Product;

import java.util.ArrayList;

public class UserDto {
    private String email;
    private String username;
    private ArrayList<Product> products;
    private int hashcode;


    public UserDto(String username, int hashcode) {
        this.username = username;
        this.hashcode = hashcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getHashcode() {
        return hashcode;
    }

    public void setHashcode(int hashcode) {
        this.hashcode = hashcode;
    }
}

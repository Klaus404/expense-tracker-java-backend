package com.example.klaus404.expensemanager.dto;


import com.example.klaus404.expensemanager.model.User;


public class ProductDto {
    private Long id;
    private User user;
    private String name;
    private float value;
    private int quantity;
    private String description;

    public ProductDto(Long id, User user, String name, float value, int quantity, String description) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

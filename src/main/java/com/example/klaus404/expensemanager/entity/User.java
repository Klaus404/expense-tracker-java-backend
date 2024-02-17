package com.example.klaus404.expensemanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @Column(name = "hashcode")
    private int hashcode;

    public List<Product> getProducts(){
        return this.products.stream()
                .collect(Collectors.toList());

    }

    public String getUsername() {
        return this.username;
    }

    public User(String username, List<Product> products, int hashcode) {
        this.username = username;
        this.products = products;
        this.hashcode = hashcode;
    }
}

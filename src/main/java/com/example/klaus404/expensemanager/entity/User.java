package com.example.klaus404.expensemanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User implements Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private Set<Product> products = new HashSet<>();

    public User() {
        // Initialize collections in the constructor to avoid NPE
        this.products = new HashSet<>();
    }

    @Override
    public String getName() {
        return this.email;
    }
}

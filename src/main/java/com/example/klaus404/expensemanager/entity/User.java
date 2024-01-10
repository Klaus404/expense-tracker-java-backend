package com.example.klaus404.expensemanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User implements Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @Override
    public String getName() {
        return this.email;
    }

    List<Product> listProducts(){
        return this.products;
    }

    @NotNull
    @Column(name = "principal_id")
    String principal_id;
}

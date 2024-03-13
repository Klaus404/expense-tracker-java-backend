package com.example.klaus404.expensemanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole role;

    public Role(){}

    public Role(ERole role_name) {
        this.role = role_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getRole_name() {
        return role;
    }

    public void setRole_name(ERole role_name) {
        this.role = role_name;
    }


    }

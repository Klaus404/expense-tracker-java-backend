package com.example.klaus404.expensemanager.model;

import com.example.klaus404.expensemanager.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    //Also used as username to log in users
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @NonNull
    @Column(name = "username", unique = true)
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @NonNull
    @Column(name = "hashcode")
    private int hashcode;

    public List<Product> getProducts(){
        return this.products.stream()
                .collect(Collectors.toList());

    }

    public String getUsername() {
        return this.username;
    }

    public User(String username, int hashcode) {
        this.username = username;
        this.hashcode = hashcode;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto toDto(){
        return new UserDto(this.username, this.hashcode);
    }
}

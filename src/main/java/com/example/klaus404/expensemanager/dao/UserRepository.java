package com.example.klaus404.expensemanager.dao;

import com.example.klaus404.expensemanager.model.Product;
import com.example.klaus404.expensemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT p FROM Product p WHERE p.user = :user")
    Optional<List<Product>> findProductsByUser(@Param("user") User user);
}

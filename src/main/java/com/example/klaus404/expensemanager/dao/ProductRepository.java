package com.example.klaus404.expensemanager.dao;

import com.example.klaus404.expensemanager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByNameContaining(@RequestParam("name") String name);

    List<Product> findProductByUser_Email(@RequestParam("user_email") String email);

    Product findProductByIdAndUser_Email(String email, @RequestParam("product_id") Long id);

}

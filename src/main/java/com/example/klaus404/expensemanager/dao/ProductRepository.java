package com.example.klaus404.expensemanager.dao;

import com.example.klaus404.expensemanager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductBy(@RequestParam("id") Long id);


}

package com.example.klaus404.expensemanager.dao;

import com.example.klaus404.expensemanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(@RequestParam("id") Long id);


}

package com.example.klaus404.expensemanager;

import com.example.klaus404.expensemanager.entity.Product;
import com.example.klaus404.expensemanager.entity.User;
import com.example.klaus404.expensemanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ExpenseManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseManagerApplication.class, args);


    }

}

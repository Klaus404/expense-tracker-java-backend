package com.example.klaus404.expensemanager.dao;

import com.example.klaus404.expensemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String name);

    boolean existsByEmail(String email);
}

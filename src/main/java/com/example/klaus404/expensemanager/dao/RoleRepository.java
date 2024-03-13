package com.example.klaus404.expensemanager.dao;

import com.example.klaus404.expensemanager.model.ERole;
import com.example.klaus404.expensemanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRole(ERole name);


}

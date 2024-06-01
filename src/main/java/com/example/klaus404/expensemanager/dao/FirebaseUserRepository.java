package com.example.klaus404.expensemanager.dao;

import com.example.klaus404.expensemanager.exception.NotFoundException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FirebaseUserRepository {
    private final Logger logger = LoggerFactory.getLogger(FirebaseUserRepository.class);
    private final FirebaseAuth firebaseAuth;

    @Autowired
    public FirebaseUserRepository(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public Optional<UserRecord> retrieveFirebaseUser(String email) throws NotFoundException {
        try {
            return Optional.of(firebaseAuth.getUserByEmail(email));
        } catch (FirebaseAuthException ex) {
            logger.error("Error retrieving user by email: {}", email, ex);
            if ("internal-error".equals(ex.getErrorCode())) {
                throw new NotFoundException("Firebase user not found by email");
            }
        }
        return Optional.empty();
    }
}

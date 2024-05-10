package com.example.klaus404.expensemanager.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final FirebaseAuth firebaseAuth;

    public AuthService() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public UserRecord getUserFromFirebaseByEmail(String email) throws FirebaseAuthException {
        //Returns the UserRecord from the idp
        return firebaseAuth.getUserByEmail(email);
    }
}

package com.example.klaus404.expensemanager.dao;


import com.example.klaus404.expensemanager.exception.NotFoundException;
import com.google.common.base.Optional;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//@Repository
public class FirebaseUserRepository {
    private Logger logger = LoggerFactory.getLogger(FirebaseUserRepository.class);
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    Optional<UserRecord> retrieveFirebaseUser(String email) throws NotFoundException{
        try{
            return Optional.of(firebaseAuth.getUserByEmail(email));
        } catch (FirebaseAuthException ex){
            if(ex.getErrorCode().name().equals("internal-error")){
                throw new NotFoundException("Firebase user not found by email");
            }
        }
        return null;
    }
}

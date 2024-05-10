package com.example.klaus404.expensemanager.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseService {

    @Value("${firebase.config.path}")
    private String firebaseConfigPath;

    @PostConstruct
    public void initializeFirebase() {
        try (FileInputStream serviceAccount = new FileInputStream(firebaseConfigPath)) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://expensemanager-d878c-default-rtdb.europe-west1.firebasedatabase.app/")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing Firebase", e);
        }
    }
}

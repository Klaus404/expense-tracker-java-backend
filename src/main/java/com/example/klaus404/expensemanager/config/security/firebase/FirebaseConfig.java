package com.example.klaus404.expensemanager.config.security.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FirebaseConfig {

    FileInputStream serviceAccount;

    {
        try {
            serviceAccount = new FileInputStream("/src/main/resources/firebaseConfig.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    FirebaseOptions options;

    {
        try {
            options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://expensemanager-d878c-default-rtdb.europe-west1.firebasedatabase.app")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

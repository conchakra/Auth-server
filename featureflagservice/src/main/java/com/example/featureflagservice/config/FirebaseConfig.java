package com.example.featureflagservice.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

import java.io.FileInputStream;

@Configuration
public class FirebaseConfig {


    @PostConstruct
    public void initialize() throws Exception {


        FileInputStream serviceAccount =
                new FileInputStream(
                "src/main/resources/firebase-service-account.json"
                );


        FirebaseOptions options =
                FirebaseOptions.builder()
                .setCredentials(
                    GoogleCredentials.fromStream(serviceAccount)
                )
                .build();


        if(FirebaseApp.getApps().isEmpty()) {

            FirebaseApp.initializeApp(options);

        }


        System.out.println("Firebase initialized");

    }

}
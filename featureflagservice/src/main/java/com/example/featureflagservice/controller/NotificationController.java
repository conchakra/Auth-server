package com.example.featureflagservice.controller;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @PostMapping("/send-test-notification")
public String sendNotification(
        @RequestParam String token) {

    try {

        Message message =
                Message.builder()
                        .setToken(token)
                        .setNotification(
                                Notification.builder()
                                        .setTitle("Test Notification")
                                        .setBody("Hello from Spring Boot")
                                        .build())
                        .build();


        String response =
                FirebaseMessaging.getInstance()
                        .send(message);


        System.out.println("FCM SUCCESS: " + response);

        return response;


    } catch(Exception e) {

        e.printStackTrace();   // IMPORTANT

        return "ERROR: " + e.getMessage();

    }
}
}

package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.Notification;
import com.example.SecurityManagementSystem.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/addNotification")
    public Notification addNotification(@RequestBody @Valid Notification notification){
        return notificationService.addNotification(notification);
    }

    @GetMapping("/getAllNotifications")
    public List<Notification> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @GetMapping("/getNotificationById/{notificationId}")
    public Notification getNotificationById(@PathVariable Long notificationId){
        return notificationService.getNotificationById(notificationId);
    }

}

package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.Notification;
import com.example.SecurityManagementSystem.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_GUARD_USER')")
    @PostMapping("/addNotification")
    public Notification addNotification(@RequestBody @Valid Notification notification){
        return notificationService.addNotification(notification);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_SOCIETY_USER')")
    @GetMapping("/getAllNotifications")
    public List<Notification> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_SOCIETY_USER')")
    @GetMapping("/getNotificationById/{notificationId}")
    public Notification getNotificationById(@PathVariable Long notificationId){
        return notificationService.getNotificationById(notificationId);
    }

}

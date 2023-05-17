package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    Notification addNotification(Notification notification);

    List<Notification> getAllNotifications();

    Notification getNotificationById(Long notificationId);
}

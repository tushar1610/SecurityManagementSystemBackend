package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import org.springframework.stereotype.Service;
@Service
public interface SocietyUserService {
    SocietyUser getSocietyUserById(Long sUserId);

    SocietyUser addSocietyUser(SocietyUser societyUser);

    SocietyUser updateSocietyUser(Long userId, SocietyUser societyUser);

    void deleteSocietyUser(Long userId);
}

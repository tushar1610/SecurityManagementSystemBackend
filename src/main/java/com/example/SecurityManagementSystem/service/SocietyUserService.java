package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.exception.SocietyUserNotFoundException;
import org.springframework.stereotype.Service;
@Service
public interface SocietyUserService {
    SocietyUser getSocietyUserById(Long sUserId) throws SocietyUserNotFoundException;

    SocietyUser addSocietyUser(SocietyUser societyUser);

    SocietyUser updateSocietyUser(Long userId, SocietyUser societyUser) throws SocietyUserNotFoundException;

    void deleteSocietyUser(Long userId) throws SocietyUserNotFoundException;
}

package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.GuardUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GuardUserService {
    List<GuardUser> getAllGuardUsers();

    GuardUser getGuardUserByUserId(Long userId);

    GuardUser addGuadUser(GuardUser guardUser);

    GuardUser updateGuardUserByUserId(Long userId, GuardUser guardUser);

    void deleteGuardUserByUserId(Long userId);
}

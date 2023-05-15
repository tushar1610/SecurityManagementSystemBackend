package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.GuardUser;
import com.example.SecurityManagementSystem.repository.GuardUserRepository;
import com.example.SecurityManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuardUserServiceImpl implements GuardUserService{

    @Autowired
    private GuardUserRepository guardUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<GuardUser> getAllGuardUsers() {
        return guardUserRepository.findAll();
    }

    @Override
    public GuardUser getGuardUserByUserId(Long userId) {
        if(userRepository.findById(userId).isPresent()){
            return guardUserRepository.findByUserUserId(userId);
        }
        return null;
    }

    @Override
    public GuardUser addGuadUser(GuardUser guardUser) {
        return guardUserRepository.save(guardUser);
    }

    @Override
    public GuardUser updateGuardUserByUserId(Long userId, GuardUser guardUser) {
        GuardUser guardUser1 = guardUserRepository.findByUserUserId(userId);
        if (guardUser1 == null) {
            return null;
        }
        if (!guardUser1.getShiftTime().equals(guardUser.getShiftTime())){
            guardUser1.setShiftTime(guardUser.getShiftTime());
        }
        if (guardUser1.getAddress().equals(guardUser.getAddress())){
            guardUser1.setAddress(guardUser.getAddress());
        }

        return guardUserRepository.save(guardUser1);
    }

    @Override
    public void deleteGuardUserByUserId(Long userId) {
        guardUserRepository.delete(guardUserRepository.findByUserUserId(userId));
        userRepository.deleteById(userId);
    }
}

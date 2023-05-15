package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.entity.User;
import com.example.SecurityManagementSystem.repository.SocietyUserRepository;
import com.example.SecurityManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocietyUserServiceImpl implements SocietyUserService{

    @Autowired
    private SocietyUserRepository societyUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public SocietyUser getSocietyUserById(Long userId) {
        if(userRepository.findById(userId).isPresent()){
            return societyUserRepository.findByUserUserId(userId);
        }
        return null;
    }

    @Override
    public SocietyUser addSocietyUser(SocietyUser societyUser) {
        User user = societyUser.getUser();
        User user1 = userRepository.save(user);
        if (user1.getUserId() == null){
            return null;
        }
        societyUser.setUser(user1);
        return societyUserRepository.save(societyUser);
    }

    @Override
    public SocietyUser updateSocietyUser(Long userId, SocietyUser societyUser) {
        SocietyUser societyUser1 = societyUserRepository.findByUserUserId(userId);
        if (societyUser1 == null) {
            return null;
        }
        if (societyUser1.getIsAdmin() != societyUser.getIsAdmin()){
            societyUser1.setIsAdmin(societyUser.getIsAdmin());
        }
        if (!societyUser1.getOwnerName().equals(societyUser.getOwnerName())){
            societyUser1.setOwnerName(societyUser.getOwnerName());
        }
        if (!societyUser1.getFlatNo().equals(societyUser.getFlatNo())){
            societyUser1.setFlatNo(societyUser.getFlatNo());
        }
        return societyUserRepository.save(societyUser1);

    }

    @Override
    public void deleteSocietyUser(Long userId) {
        societyUserRepository.delete(societyUserRepository.findByUserUserId(userId));
        userRepository.deleteById(userId);
    }
}

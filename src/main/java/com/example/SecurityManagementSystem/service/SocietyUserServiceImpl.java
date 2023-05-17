package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.entity.User;
import com.example.SecurityManagementSystem.exception.SocietyUserNotFoundException;
import com.example.SecurityManagementSystem.repository.SocietyUserRepository;
import com.example.SecurityManagementSystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocietyUserServiceImpl implements SocietyUserService{

    public static final Logger logger = LoggerFactory.getLogger(SocietyUserServiceImpl.class);

    @Autowired
    private SocietyUserRepository societyUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public SocietyUser getSocietyUserById(Long userId) throws SocietyUserNotFoundException {
        Optional<SocietyUser> societyUser = societyUserRepository.findByUserUserId(userId);
        if (!societyUser.isPresent()) {
            logger.error("No such society member.");
            throw new SocietyUserNotFoundException("Society member not found");
        }
        logger.debug(societyUser.get().toString());
        return societyUser.get();
    }

    @Override
    public SocietyUser addSocietyUser(SocietyUser societyUser) {
        User user = societyUser.getUser();
        User user1 = userRepository.save(user);
        if (user1.getUserId() == null){
            logger.error("User data cannot be added.");
            return null;
        }
        societyUser.setUser(user1);
        return societyUserRepository.save(societyUser);
    }

    @Override
    public SocietyUser updateSocietyUser(Long userId, SocietyUser societyUser) throws SocietyUserNotFoundException {
        Optional<SocietyUser> societyUser1 = societyUserRepository.findByUserUserId(userId);
        if (!societyUser1.isPresent()) {
            logger.error("No such society member.");
            throw new SocietyUserNotFoundException("Society member not found");
        }
        if (societyUser1.get().getIsAdmin() != societyUser.getIsAdmin()){
            societyUser1.get().setIsAdmin(societyUser.getIsAdmin());
        }
        if (!societyUser1.get().getOwnerName().equals(societyUser.getOwnerName())){
            societyUser1.get().setOwnerName(societyUser.getOwnerName());
        }
        if (!societyUser1.get().getFlatNo().equals(societyUser.getFlatNo())){
            societyUser1.get().setFlatNo(societyUser.getFlatNo());
        }
        return societyUserRepository.save(societyUser1.get());

    }

    @Override
    public void deleteSocietyUser(Long userId) throws SocietyUserNotFoundException {
        Optional<SocietyUser> societyUser = societyUserRepository.findByUserUserId(userId);
        if (!societyUser.isPresent()) {
            logger.error("No such society member.");
            throw new SocietyUserNotFoundException("Society member not found");
        }
        societyUserRepository.delete(societyUser.get());
        userRepository.deleteById(userId);
        logger.info("Society member deletion successful.");
    }
}

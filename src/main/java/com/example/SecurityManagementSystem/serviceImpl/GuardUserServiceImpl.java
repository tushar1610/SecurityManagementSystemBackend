package com.example.SecurityManagementSystem.serviceImpl;

import com.example.SecurityManagementSystem.entity.GuardUser;
import com.example.SecurityManagementSystem.exception.GuardUserNotFoundException;
import com.example.SecurityManagementSystem.repository.GuardUserRepository;
import com.example.SecurityManagementSystem.repository.UserRepository;
import com.example.SecurityManagementSystem.service.GuardUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuardUserServiceImpl implements GuardUserService {

    public static final Logger logger = LoggerFactory.getLogger(GuardUserServiceImpl.class);

    @Autowired
    private GuardUserRepository guardUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<GuardUser> getAllGuardUsers() {
        return guardUserRepository.findAll();
    }

    @Override
    public GuardUser getGuardUserByUserId(Long userId) throws GuardUserNotFoundException {
        Optional<GuardUser> guardUser = guardUserRepository.findByUserUserId(userId);
        if(!guardUser.isPresent()){
            logger.error("No such guard.");
            throw new GuardUserNotFoundException("Guard member not found.");
        }
        logger.debug(guardUser.get().toString());
        return guardUser.get();
    }

    @Override
    public GuardUser addGuardUser(GuardUser guardUser) {
        return guardUserRepository.save(guardUser);
    }

    @Override
    public GuardUser updateGuardUserByUserId(Long userId, GuardUser guardUser) throws GuardUserNotFoundException {
        Optional<GuardUser> guardUser1 = guardUserRepository.findByUserUserId(userId);
        if(!guardUser1.isPresent()){
            logger.error("No such guard.");
            throw new GuardUserNotFoundException("Guard member not found.");
        }
        if (!guardUser1.get().getShiftTime().equals(guardUser.getShiftTime())){
            guardUser1.get().setShiftTime(guardUser.getShiftTime());
        }
        if (!guardUser1.get().getAddress().equals(guardUser.getAddress())){
            guardUser1.get().setAddress(guardUser.getAddress());
        }
        return guardUserRepository.save(guardUser1.get());
    }

    @Override
    public void deleteGuardUserByUserId(Long userId) throws GuardUserNotFoundException {
        Optional<GuardUser> guardUser = guardUserRepository.findByUserUserId(userId);
        if (!guardUser.isPresent()) {
            logger.error("No such guard.");
            throw new GuardUserNotFoundException("Guard member not found");
        }
        guardUserRepository.delete(guardUserRepository.findByUserUserId(userId).get());
        userRepository.deleteById(userId);
        logger.info("Guard deleted successful.");
    }
}

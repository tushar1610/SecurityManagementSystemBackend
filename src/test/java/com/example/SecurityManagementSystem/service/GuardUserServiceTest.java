package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.GuardUser;
import com.example.SecurityManagementSystem.entity.User;
import com.example.SecurityManagementSystem.repository.GuardUserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuardUserServiceTest {

    @Autowired
    private GuardUserService guardUserService;

    @MockBean
    private GuardUserRepository guardUserRepository;

    @BeforeEach
    void setUp(){

        User user = User.builder()
                .userId(1L)
                .userName("User1")
                .build();

        GuardUser guardUser = GuardUser.builder()
                .address("Address")
                .shiftTime("shiftTime")
                .user(user)
                .build();

        Mockito.when(guardUserRepository.findByUserUserId(1L)).thenReturn(Optional.ofNullable(guardUser));

    }

    @Test
    void getGuardUserByUserId() {
        Long userId = 1L;
        GuardUser guardUser = guardUserRepository.findByUserUserId(userId).get();
        assertEquals(userId, guardUser.getUser().getUserId());
    }

}
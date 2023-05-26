package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.exception.SocietyUserNotCreatedException;
import com.example.SecurityManagementSystem.exception.SocietyUserNotFoundException;
import com.example.SecurityManagementSystem.service.SocietyUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SocietyUserController {

    @Autowired
    private SocietyUserService societyUserService;

    @GetMapping("/getSocietyUserById/{userId}")
    public SocietyUser getSocietyUserById(@PathVariable Long userId) throws SocietyUserNotFoundException {
        return societyUserService.getSocietyUserById(userId);
    }

    @PostMapping("/addSocietyUser")
    public SocietyUser addSocietyUser(@RequestBody @Valid SocietyUser societyUser) throws SocietyUserNotCreatedException {
        SocietyUser societyUser1 = societyUserService.addSocietyUser(societyUser);
        if (societyUser1 == null) {
            throw new SocietyUserNotCreatedException("Society member cannot be created. Try again later.");
        }
        return societyUser1;
    }

    @PutMapping("/updateSocietyUserByUserId/{userId}")
    public SocietyUser updateSocietyUserByUserId(@PathVariable Long userId, @RequestBody @Valid SocietyUser societyUser) throws SocietyUserNotFoundException {

        return societyUserService.updateSocietyUserByUserId(userId, societyUser);
    }

    @DeleteMapping("/deleteSocietyUser/{userId}")
    public String deleteSocietyUser(@PathVariable Long userId){
        try{
            societyUserService.deleteSocietyUser(userId);
        } catch (Exception e){
            return "Error in deleting the user";
        }
        return "Deletion successful";
    }

}

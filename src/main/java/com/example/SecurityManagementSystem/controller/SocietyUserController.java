package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.service.SocietyUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SocietyUserController {

    @Autowired
    private SocietyUserService societyUserService;

    @GetMapping("/getSocietyUserById/{userId}")
    public SocietyUser getSocietyUserById(@PathVariable Long userId){
        SocietyUser societyUser = societyUserService.getSocietyUserById(userId);
        if (societyUser == null) {
            //return SocietyUserNotFoundException
        }
        return societyUser;
    }

    @PostMapping("/addSocietyUser")
    public SocietyUser addSocietyUser(@RequestBody @Valid SocietyUser societyUser){
        SocietyUser societyUser1 = societyUserService.addSocietyUser(societyUser);
        if (societyUser1 == null) {
            //return SocietyUserNotCreatedException
        }
        return societyUser1;
    }

    @PutMapping("/updateSocietyUser/{userId}")
    public SocietyUser updateSocietyUser(@PathVariable Long userId, @RequestBody @Valid SocietyUser societyUser){
        SocietyUser societyUser1 = societyUserService.updateSocietyUser(userId, societyUser);
        if (societyUser1 == null) {
            //return UserNotUpdatedException
        }
        return societyUser1;
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

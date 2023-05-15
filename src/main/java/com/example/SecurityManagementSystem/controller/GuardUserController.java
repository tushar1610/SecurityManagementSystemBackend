package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.GuardUser;
import com.example.SecurityManagementSystem.service.GuardUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuardUserController {

    @Autowired
    private GuardUserService guardUserService;

    @GetMapping("/getAllGuardUsers")
    public List<GuardUser> getAllGuardUsers(){
        return guardUserService.getAllGuardUsers();
    }

    @GetMapping("/getGuardUserById/{userId}")
    public GuardUser getGuardUserByUserId(@PathVariable Long userId){
        GuardUser guardUser = guardUserService.getGuardUserByUserId(userId);
        if (guardUser == null) {
            //return GuardUserNotFoundException
        }
        return guardUser;
    }

    @PostMapping("/addGuardUser")
    public GuardUser addGuardUser(@RequestBody GuardUser guardUser){
        GuardUser guardUser1 = guardUserService.addGuadUser(guardUser);
        if (guardUser1 == null) {
            //return failed to add guard
        }
        return guardUser1;
    }

    @PutMapping("/updateGuardUserByUserId/{userId}")
    public GuardUser updateGuardUserByUserId(@PathVariable Long userId, @RequestBody GuardUser guardUser){
        GuardUser guardUser1 = guardUserService.updateGuardUserByUserId(userId, guardUser);
        if (guardUser1 == null) {
            //return GuardUserNotFoundException
        }
        return guardUser1;
    }

    @DeleteMapping("/deleteGuardUserByUserId/{userId}")
    public String deleteGuardUserByUserId(@PathVariable Long userId){
        guardUserService.deleteGuardUserByUserId(userId);
        return "Guard User deleted successfully";
    }

}

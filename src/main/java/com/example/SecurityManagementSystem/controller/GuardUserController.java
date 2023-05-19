package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.GuardUser;
import com.example.SecurityManagementSystem.exception.GuardUserNotCreatedException;
import com.example.SecurityManagementSystem.exception.GuardUserNotFoundException;
import com.example.SecurityManagementSystem.service.GuardUserService;
import jakarta.validation.Valid;
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
    public GuardUser getGuardUserByUserId(@PathVariable Long userId) throws GuardUserNotFoundException {

        return guardUserService.getGuardUserByUserId(userId);
    }

    @PostMapping("/addGuardUser")
    public GuardUser addGuardUser(@RequestBody @Valid GuardUser guardUser) throws GuardUserNotCreatedException {
        GuardUser guardUser1 = guardUserService.addGuardUser(guardUser);
        if (guardUser1 == null) {
            throw new GuardUserNotCreatedException("Guard member cannot be created. Try again later.");
        }
        return guardUser1;
    }

    @PutMapping("/updateGuardUserByUserId/{userId}")
    public GuardUser updateGuardUserByUserId(@PathVariable Long userId, @RequestBody GuardUser guardUser) throws GuardUserNotFoundException {

        return guardUserService.updateGuardUserByUserId(userId, guardUser);
    }

    @DeleteMapping("/deleteGuardUserByUserId/{userId}")
    public String deleteGuardUserByUserId(@PathVariable Long userId) throws GuardUserNotFoundException {
        guardUserService.deleteGuardUserByUserId(userId);
        return "Guard User deleted successfully";
    }

}

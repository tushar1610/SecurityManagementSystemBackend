package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.GuardUser;
import com.example.SecurityManagementSystem.exception.GuardUserNotCreatedException;
import com.example.SecurityManagementSystem.exception.GuardUserNotFoundException;
import com.example.SecurityManagementSystem.service.GuardUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuardUserController {

    @Autowired
    private GuardUserService guardUserService;

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllGuardUsers")
    public List<GuardUser> getAllGuardUsers(){
        return guardUserService.getAllGuardUsers();
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_GUARD_USER')")
    @GetMapping("/getGuardUserById/{userId}")
    public GuardUser getGuardUserByUserId(@PathVariable Long userId) throws GuardUserNotFoundException {

        return guardUserService.getGuardUserByUserId(userId);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
    @PostMapping("/addGuardUser")
    public GuardUser addGuardUser(@RequestBody @Valid GuardUser guardUser) throws GuardUserNotCreatedException {
        GuardUser guardUser1 = guardUserService.addGuardUser(guardUser);
        if (guardUser1 == null) {
            throw new GuardUserNotCreatedException("Guard member cannot be created. Try again later.");
        }
        return guardUser1;
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_GUARD_USER')")
    @PutMapping("/updateGuardUserByUserId/{userId}")
    public GuardUser updateGuardUserByUserId(@PathVariable Long userId, @RequestBody GuardUser guardUser) throws GuardUserNotFoundException {

        return guardUserService.updateGuardUserByUserId(userId, guardUser);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_GUARD_USER')")
    @DeleteMapping("/deleteGuardUserByUserId/{userId}")
    public String deleteGuardUserByUserId(@PathVariable Long userId) throws GuardUserNotFoundException {
        guardUserService.deleteGuardUserByUserId(userId);
        return "Guard User deleted successfully";
    }

}

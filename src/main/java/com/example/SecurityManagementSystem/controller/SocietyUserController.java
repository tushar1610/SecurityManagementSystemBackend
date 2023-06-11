package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.Login;
import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.exception.SocietyUserNotCreatedException;
import com.example.SecurityManagementSystem.exception.SocietyUserNotFoundException;
import com.example.SecurityManagementSystem.service.SocietyUserService;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/society/user")
public class SocietyUserController {

    @Autowired
    private SocietyUserService societyUserService;

    public static final Logger logger = LoggerFactory.getLogger(SocietyUserController.class);

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_SOCIETY_USER')")
    @GetMapping("/getSocietyUserById/{userId}")
    public SocietyUser getSocietyUserById(@PathVariable Long userId) throws SocietyUserNotFoundException {
        return societyUserService.getSocietyUserById(userId);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_SOCIETY_USER')")
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllSocietyUsers")
    public List<SocietyUser> getAllSocietyUsers(){
        return societyUserService.getAllSocietyUsers();
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
    @PostMapping("/addSocietyUser")
    public SocietyUser addSocietyUser(@RequestBody @Valid SocietyUser societyUser) throws SocietyUserNotCreatedException {
        SocietyUser societyUser1 = societyUserService.addSocietyUser(societyUser);
        if (societyUser1 == null) {
            throw new SocietyUserNotCreatedException("Society member cannot be created. Try again later.");
        }
        return societyUser1;
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_SOCIETY_USER')")
    @PutMapping("/updateSocietyUserByUserId/{userId}")
    public SocietyUser updateSocietyUserByUserId(@PathVariable Long userId, @RequestBody @Valid SocietyUser societyUser) throws SocietyUserNotFoundException {

        return societyUserService.updateSocietyUserByUserId(userId, societyUser);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

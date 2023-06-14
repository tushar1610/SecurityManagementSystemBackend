package com.example.SecurityManagementSystem.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.example.SecurityManagementSystem.entity.Login;
import com.example.SecurityManagementSystem.entity.User;
import com.example.SecurityManagementSystem.exception.NoSuchUserException;
import com.example.SecurityManagementSystem.exception.UserNotAuthorizedException;
import com.example.SecurityManagementSystem.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(HttpServletRequest request, @RequestBody Login login) throws UsernameNotFoundException{
        Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(
            login.getUsername(), login.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        //To check the role of the user logged in
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        String role = "";
//        for(GrantedAuthority authority : authorities){
//            String authorityName = authority.getAuthority();
//            role = authorityName;
//        }
//        System.out.println(role);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<Object>(principal, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get/details/{email}")
    public ResponseEntity<User> getDetails(@PathVariable("email") String email) throws NoSuchUserException, UserNotAuthorizedException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotAuthorizedException("Access denied. Unauthorized access.");
        }
        User user = userService.getDetails(email);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}

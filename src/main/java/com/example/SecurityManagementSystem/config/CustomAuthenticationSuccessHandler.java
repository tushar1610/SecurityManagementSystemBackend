package com.example.SecurityManagementSystem.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            response.sendRedirect("http://localhost:3000/userPage");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SOCIETY_USER"))) {
            response.sendRedirect("http://localhost:3000/userPage");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_GUARD_USER"))) {
            response.sendRedirect("http://localhost:3000/guardPage");
        }
    }
}

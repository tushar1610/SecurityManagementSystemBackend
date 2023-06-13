package com.example.SecurityManagementSystem.config;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;


    @Bean
    public UserDetailsService getUserDetailsService(){
        return new SecurityUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                //.addFilterBefore(customSecurityFilter, AbstractPreAuthenticatedProcessingFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/user/login")
                .permitAll()
                .requestMatchers("/society/user/add")
                .permitAll()
                .requestMatchers("/guard/user/add")
                .permitAll()
                .anyRequest()
                .authenticated();
                // .loginPage("http://localhost:3000/login")
                //.loginProcessingUrl("/loginUser");
                //.successHandler(authenticationSuccessHandler)
                // .failureUrl("/login?error");
                //.defaultSuccessUrl("/index", true);

        return httpSecurity.build();
    }

}

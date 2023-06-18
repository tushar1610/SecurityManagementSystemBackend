package com.example.SecurityManagementSystem.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// @Configuration
public class CorsConfig {

//     @Bean
//     public CorsFilter corsFilter(){
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         CorsConfiguration configuration = new CorsConfiguration();
// //        configuration.setAllowCredentials(true);
// //        configuration.addAllowedOrigin("http://localhost:3000");
// //        configuration.addAllowedHeader("*");
// //        configuration.setAllowedHeaders(Arrays.asList("*"));
// //        configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:3000"));
// //        configuration.setAllowedMethods(Arrays.asList("POST", "OPTIONS", "GET", "PUT", "DELETE"));
//         source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
//         return new CorsFilter(source);
//     }

//    @Bean
//    public CorsFilter corsFilter(){
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        configuration.addAllowedOrigin("http://localhost:3000");
//        configuration.addAllowedHeader("*");
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:3000"));
//        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
//        source.registerCorsConfiguration("/**", configuration);
//        return new CorsFilter(source);
//    }
    
}

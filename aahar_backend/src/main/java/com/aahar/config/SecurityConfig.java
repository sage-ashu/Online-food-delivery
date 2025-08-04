package com.aahar.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf().disable() // Disable CSRF for now
	            .authorizeHttpRequests(auth -> auth
	                .anyRequest().permitAll() // 🔓 Allow all requests
	            )
	            .formLogin().disable()  // Optional: Disable form login
	            .httpBasic().disable(); // Optional: Disable basic auth

	        return http.build();
	    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
package com.jdbcAuthentication.jdbcAuthentication.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class passwordEncoder {
   @Bean
    public BCryptPasswordEncoder encoder(){
       return new BCryptPasswordEncoder();
   }
}

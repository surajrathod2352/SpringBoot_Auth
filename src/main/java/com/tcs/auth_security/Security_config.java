package com.tcs.auth_security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class Security_config {

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails adminUser = User.withUsername("Suraj")
        .password(passwordEncoder().encode("Suraj@2352"))
        .roles("ADMIN")
        .build();

        UserDetails normalUser = User.withUsername("Arun")
        .password(passwordEncoder().encode("Arun@2352"))
        .roles("USER")
        .build();

       

        return  new InMemoryUserDetailsManager(adminUser, normalUser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                // .requestMatchers("/security/admin")//after admin you can add  "/** " so that any url after that is accesable
                // .hasRole("ADMIN")
                // .requestMatchers("/security/user")
                // .hasRole("USER")
                // .requestMatchers("/security/public")
                // .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

        return httpSecurity.build();
    }
}

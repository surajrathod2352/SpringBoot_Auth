package com.tcs.auth_security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/security")
public class security_controller {
    
    @PreAuthorize("hasRole('USER')")
    // this is another whay to manage role of the accesser
    @GetMapping("/user")
    public StringBuilder getMethoduser() {
        return new StringBuilder("Security enabled for user");
    }

    @PreAuthorize("jasRole('ADMIN')")
    @GetMapping("/admin")
    public StringBuilder getMethodadmin() {
        return new StringBuilder("Security enabled admin");
    }

    @GetMapping("/public")
    public StringBuilder getMethodpublic() {
        return new StringBuilder("Security enabled for pulic");
    }
    
}

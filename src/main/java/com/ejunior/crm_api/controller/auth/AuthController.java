package com.ejunior.crm_api.controller.auth;

import com.ejunior.crm_api.model.User;
import com.ejunior.crm_api.service.auth.AuthService;
import com.ejunior.crm_api.service.auth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(jwtService.generateToken(loginRequest.getUsername()));
        }
        return ResponseEntity.status(401).body("Authentication failed");
    }

    // Você pode criar um endpoint de registro
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = authService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }
}
package com.mohamedahmed.libraryManagementSystem.controller;

import com.mohamedahmed.libraryManagementSystem.dto.RegisterDto;
import com.mohamedahmed.libraryManagementSystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto){
        String response=authService.register(registerDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

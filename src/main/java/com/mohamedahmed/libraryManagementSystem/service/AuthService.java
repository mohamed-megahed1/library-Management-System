package com.mohamedahmed.libraryManagementSystem.service;

import com.mohamedahmed.libraryManagementSystem.dto.LogInDTO;
import com.mohamedahmed.libraryManagementSystem.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String logIn(LogInDTO logInDTO);
}

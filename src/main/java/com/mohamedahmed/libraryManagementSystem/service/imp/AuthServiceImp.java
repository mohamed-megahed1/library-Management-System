package com.mohamedahmed.libraryManagementSystem.service.imp;

import com.mohamedahmed.libraryManagementSystem.entities.Patron;
import com.mohamedahmed.libraryManagementSystem.exceptions.ThisEntityAlreadyExistsException;
import com.mohamedahmed.libraryManagementSystem.service.AuthService;
import com.mohamedahmed.libraryManagementSystem.dto.LogInDTO;
import com.mohamedahmed.libraryManagementSystem.dto.RegisterDto;
import com.mohamedahmed.libraryManagementSystem.repository.PatronRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImp implements AuthService {

    private final PatronRepo patronRepo;


    @Override
    public String register(RegisterDto registerDto) {
        Patron  patron=new Patron();
        if (patronRepo.existsByEmail(registerDto.getEmail())||
                patronRepo.existsByUsername(registerDto.getUsername())){
            throw new ThisEntityAlreadyExistsException("This username or email is already exists .");

        }else {

            patron.setEmail(registerDto.getEmail());
            patron.setUsername(registerDto.getUsername());
            patron.setFirstName(registerDto.getFirstName());
            patron.setLastName(registerDto.getLastName());
            patron.setPassword(registerDto.getPassword());
            patron.setPhoneNumber(registerDto.getPhoneNumber());
            patron.setRole("ROLE_PATRON");
            patronRepo.save(patron);
            return "User Registered  Successfully";
        }

    }

    @Override
    public String logIn(LogInDTO logInDTO) {
        return null;
    }
}

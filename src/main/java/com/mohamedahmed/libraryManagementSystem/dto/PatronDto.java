package com.mohamedahmed.libraryManagementSystem.dto;


import com.mohamedahmed.libraryManagementSystem.entities.BorrowingRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatronDto {

    private Long id;

    @NotBlank(message = "Username is required")

    private String username;
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")

    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Phone number should be valid")
    private String phoneNumber;


    private Set<BorrowingRecord> booksBorrowed;

    @NotBlank(message = "Role is required")
    private String role;
}

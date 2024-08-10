package com.mohamedahmed.libraryManagementSystem.service;

import com.mohamedahmed.libraryManagementSystem.dto.PatronDto;

import java.util.List;

public interface AdminService {

    List<PatronDto> getAllPatrons();
    PatronDto getPatronById(Long id);
    String deletePatronById(Long id);

}

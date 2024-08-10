package com.mohamedahmed.libraryManagementSystem.service;

import com.mohamedahmed.libraryManagementSystem.dto.PatronDto;

public interface PatronService {

    PatronDto updatePatronInfo(PatronDto patronDto,Long id);

}

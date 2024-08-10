package com.mohamedahmed.libraryManagementSystem.service.imp;

import com.mohamedahmed.libraryManagementSystem.dto.PatronDto;
import com.mohamedahmed.libraryManagementSystem.entities.Patron;
import com.mohamedahmed.libraryManagementSystem.exceptions.NotFoundResourceException;
import com.mohamedahmed.libraryManagementSystem.mapper.PatronMapper;
import com.mohamedahmed.libraryManagementSystem.repository.PatronRepo;
import com.mohamedahmed.libraryManagementSystem.service.PatronService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatronServiceImp implements PatronService {

    private final PatronMapper patronMapper;
    private final PatronRepo patronRepo;



    @Override
    public PatronDto updatePatronInfo(PatronDto patronDto, Long id) {
        Patron patron=patronRepo.findById(id).orElseThrow(
                () -> new NotFoundResourceException("This patron with id : "+id+" is not exists")
        );
        Patron updatedPatron=patronMapper.updatePatronFromPatronDto(patronDto,patron);
        Patron newPatron= patronRepo.save(updatedPatron);
        return patronMapper.fromPatronToPatronDto(newPatron);
    }



}

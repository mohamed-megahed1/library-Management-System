package com.mohamedahmed.libraryManagementSystem.service.imp;

import com.mohamedahmed.libraryManagementSystem.dto.PatronDto;
import com.mohamedahmed.libraryManagementSystem.entities.Book;
import com.mohamedahmed.libraryManagementSystem.entities.Patron;
import com.mohamedahmed.libraryManagementSystem.exceptions.NotFoundResourceException;
import com.mohamedahmed.libraryManagementSystem.mapper.PatronMapper;
import com.mohamedahmed.libraryManagementSystem.repository.PatronRepo;
import com.mohamedahmed.libraryManagementSystem.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AdminServiceImp implements AdminService {

    private final PatronRepo patronRepo;
    private final PatronMapper patronMapper;




    @Override
    public List<PatronDto> getAllPatrons() {
        List<Patron>patrons=patronRepo.findAll();
        if (patrons.isEmpty() || patrons ==null){
            throw new NotFoundResourceException("No patrons on the system");
        }
        return patronMapper.fromPatronToPatronDto(patrons);

    }

    @Override
    public PatronDto getPatronById(Long id) {
        Patron patron=patronRepo.findById(id).
                orElseThrow(() -> new NotFoundResourceException("This patron With this id : "+id+" is not exists"));
        return patronMapper.fromPatronToPatronDto(patron);

    }

    @Override
    public String deletePatronById(Long id) {
        if(patronRepo.existsById(id)){
            patronRepo.deleteById(id);
            return "Patron deleted successfully";
        }else {
            return "Patron is not exists";
        }
    }
}

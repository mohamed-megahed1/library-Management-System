package com.mohamedahmed.libraryManagementSystem.controller;

import com.mohamedahmed.libraryManagementSystem.dto.PatronDto;
import com.mohamedahmed.libraryManagementSystem.service.PatronService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PatronController {

    private final PatronService patronService;

    @PutMapping("/patrons/{id}")
    public ResponseEntity<PatronDto>updatePatronsInfo(@RequestBody @Valid  PatronDto patronDto, @PathVariable Long id){
        PatronDto patronDto1=patronService.updatePatronInfo(patronDto,id);

        return new ResponseEntity<>(patronDto1, HttpStatus.OK);
    }
}

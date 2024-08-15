package com.mohamedahmed.libraryManagementSystem.controller;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import com.mohamedahmed.libraryManagementSystem.dto.PatronDto;
import com.mohamedahmed.libraryManagementSystem.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/patrons")
    public ResponseEntity<List<PatronDto>> getAllPatrons(){
        List<PatronDto>patronDtos=adminService.getAllPatrons();

        return new ResponseEntity<>(patronDtos, HttpStatus.OK);
    }
    @GetMapping("/patrons/{id}")
    public ResponseEntity<PatronDto> getPatronById(@PathVariable Long id){
        PatronDto patronDto=adminService.getPatronById(id);

        return new ResponseEntity<>(patronDto,HttpStatus.OK);
    }

    @DeleteMapping("/patrons/{id}")
    public ResponseEntity<String> deletePatronById(@PathVariable Long id){
        String msg=adminService.deletePatronById(id);

        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}

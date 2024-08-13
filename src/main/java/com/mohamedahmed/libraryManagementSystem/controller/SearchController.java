package com.mohamedahmed.libraryManagementSystem.controller;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import com.mohamedahmed.libraryManagementSystem.service.SearchBookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class SearchController {

    private final SearchBookService searchBookService;

    @PostMapping("/search")
    public ResponseEntity<List<BookDto>>searchBook(@RequestBody BookDto bookDto){
        List<BookDto>bookDtos=searchBookService.SerachBook(bookDto);
        return ResponseEntity.ok(bookDtos);
    }
}

package com.mohamedahmed.libraryManagementSystem.controller;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import com.mohamedahmed.libraryManagementSystem.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;



    @PostMapping("/books")
    public ResponseEntity<String> addNewBook(@Valid @RequestBody BookDto bookDto){
        String response=bookService.addNewBook(bookDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto>bookDtos=bookService.getAllBooks();

        return new ResponseEntity<>(bookDtos,HttpStatus.OK);
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id){
        BookDto bookDto=bookService.getBookById(id);

        return new ResponseEntity<>(bookDto,HttpStatus.OK);
    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id){
        String msg=bookService.removeBookById(id);

        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
    @PutMapping("/books/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto book,@PathVariable Long id){
        BookDto bookDto=bookService.updateBook(book,id);

        return new ResponseEntity<>(bookDto,HttpStatus.OK);
    }
}

package com.mohamedahmed.libraryManagementSystem.controller;

import com.mohamedahmed.libraryManagementSystem.dto.BorrowingRecordDto;
import com.mohamedahmed.libraryManagementSystem.service.BorrowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BorrowingController {

    private final BorrowService borrowService;
    @PostMapping("borrow/{book_id}/patron/{patron_id}")
    public ResponseEntity<BorrowingRecordDto>borrowingBook(@PathVariable("book_id") Long bookId,
                                                           @PathVariable("patron_id") Long patronId){
        BorrowingRecordDto borrowingRecordDto=borrowService.borrowBook(bookId,patronId);
        return new ResponseEntity<>(borrowingRecordDto, HttpStatus.OK);
    }

    @PutMapping("return/{book_id}/patron/{patron_id}")
    public ResponseEntity<BorrowingRecordDto>returnBook(@PathVariable("book_id") Long bookId,
                                                           @PathVariable("patron_id") Long patronId){
        BorrowingRecordDto borrowingRecordDto=borrowService.returnBook(bookId,patronId);
        return new ResponseEntity<>(borrowingRecordDto, HttpStatus.OK);
    }
    @PostMapping("report/{book_id}/patron/{patron_id}")
    public ResponseEntity<String>reportBookLost(@PathVariable("book_id") Long bookId,
                                                        @PathVariable("patron_id") Long patronId){
        String reported=borrowService.reportLostTheBook(bookId,patronId);
        return new ResponseEntity<>(reported, HttpStatus.OK);
    }
}

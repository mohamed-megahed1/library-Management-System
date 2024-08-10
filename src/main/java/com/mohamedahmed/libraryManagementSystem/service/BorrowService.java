package com.mohamedahmed.libraryManagementSystem.service;

import com.mohamedahmed.libraryManagementSystem.dto.BorrowingRecordDto;

public interface BorrowService {

    BorrowingRecordDto borrowBook(Long bookId,Long patronId);
    BorrowingRecordDto returnBook(Long bookId,Long patronId);
}

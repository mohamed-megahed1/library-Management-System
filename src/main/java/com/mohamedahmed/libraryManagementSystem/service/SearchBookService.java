package com.mohamedahmed.libraryManagementSystem.service;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;

import java.util.List;

public interface SearchBookService {


    List<BookDto> SerachBook(BookDto bookDto);
}

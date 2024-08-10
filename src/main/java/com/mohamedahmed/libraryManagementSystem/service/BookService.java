package com.mohamedahmed.libraryManagementSystem.service;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import com.mohamedahmed.libraryManagementSystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookService  {

    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    String addNewBook(BookDto bookDto);
    BookDto updateBook(BookDto bookDto,Long id);

    String removeBookById(Long id);
}

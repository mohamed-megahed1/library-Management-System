package com.mohamedahmed.libraryManagementSystem.service.imp;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import com.mohamedahmed.libraryManagementSystem.entities.Book;
import com.mohamedahmed.libraryManagementSystem.exceptions.NotFoundResourceException;
import com.mohamedahmed.libraryManagementSystem.exceptions.ThisEntityAlreadyExistsException;
import com.mohamedahmed.libraryManagementSystem.mapper.BookMapper;
import com.mohamedahmed.libraryManagementSystem.repository.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImpTest {

    @Mock

    private BookRepo bookRepo;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImp bookService;

    private static Book book;
    private static BookDto bookDto;

    @BeforeEach
    void setUp() {

        book = new Book(1L, "Title", "Author", "1234567890", "Publisher",
                LocalDate.of(2023, 8, 10), "Edition", "Category",
                300, "English", 10, 10, "Description");
        bookDto = new BookDto(1L, "Title", "Author", "1234567890", "Publisher",
                LocalDate.of(2023, 8, 10), "Edition", "Category",
                300, "English", 10, 10, "Description");
    }

    @Test
    void testGetAllBooks() {
        when(bookRepo.findAll()).thenReturn(List.of(book));
        when(bookMapper.fromBookToBookDto(List.of(book))).thenReturn(List.of(bookDto));

        List<BookDto> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(bookDto, result.get(0));
    }

    @Test
    void testGetBookById_Success() {
        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.fromBookToBookDto(book)).thenReturn(bookDto);

        BookDto result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals(bookDto, result);
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookRepo.findById(1L)).thenReturn(Optional.empty());

        NotFoundResourceException thrown = assertThrows(
                NotFoundResourceException.class,
                () -> bookService.getBookById(1L),
                "Expected getBookById() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("This book With this id : 1 is not exists"));
    }

    @Test
    void testAddNewBook_Success() {
        when(bookRepo.existsByIsbn("1234567890")).thenReturn(false);
        when(bookMapper.fromBookDtoToBook(bookDto)).thenReturn(book);

        String result = bookService.addNewBook(bookDto);

        verify(bookRepo, times(1)).save(book);
        assertEquals("Book added Successfully", result);
    }

    @Test
    void testAddNewBook_AlreadyExists() {
        when(bookRepo.existsByIsbn("1234567890")).thenReturn(true);

        ThisEntityAlreadyExistsException thrown = assertThrows(
                ThisEntityAlreadyExistsException.class,
                () -> bookService.addNewBook(bookDto),
                "Expected addNewBook() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("This book with this isbn code 1234567890 is already exists ."));
    }

    @Test
    void testUpdateBook_Success() {
        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.updateBookFromBookDto(bookDto, book)).thenReturn(book);
        when(bookRepo.save(book)).thenReturn(book);
        when(bookMapper.fromBookToBookDto(book)).thenReturn(bookDto);

        BookDto result = bookService.updateBook(bookDto, 1L);

        assertNotNull(result);
        assertEquals(bookDto, result);
    }

    @Test
    void testUpdateBook_NotFound() {
        when(bookRepo.findById(1L)).thenReturn(Optional.empty());

        NotFoundResourceException thrown = assertThrows(
                NotFoundResourceException.class,
                () -> bookService.updateBook(bookDto, 1L),
                "Expected updateBook() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("This book With this id : 1 is not exists"));
    }

    @Test
    void testRemoveBookById_Success() {
        when(bookRepo.existsById(1L)).thenReturn(true);

        String result = bookService.removeBookById(1L);

        verify(bookRepo, times(1)).deleteById(1L);
        assertEquals("book deleted successfully", result);
    }

    @Test
    void testRemoveBookById_NotFound() {
        when(bookRepo.existsById(1L)).thenReturn(false);

        String result = bookService.removeBookById(1L);

        assertEquals("book is not exists", result);
    }


}

package com.mohamedahmed.libraryManagementSystem.mapper;
import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import com.mohamedahmed.libraryManagementSystem.entities.Book;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class BookMapperTest {
   @Autowired
    private BookMapper bookMapper ;
    static Book book = new Book();
    static Book book2 = new Book();
    static  BookDto bookDto = new BookDto();

    static  List<Book> books = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        book.setId(1L);
        book.setTitle("Test Title 1");
        book.setAuthor("Author 1");
        book.setIsbn("9781234567890");
        book.setPublisher("Publisher 1");
        book.setPublishedDate(LocalDate.of(2021, 1, 1));
        book.setEdition("First Edition");
        book.setCategory("Category 1");
        book.setPages(100);
        book.setLanguage("English");
        book.setAvailableCopies(10);
        book.setTotalCopies(15);
        book.setDescription("Description 1");

        book2.setId(2L);
        book2.setTitle("Test Title 2");
        book2.setAuthor("Author 2");
        book2.setIsbn("9780987654321");
        book2.setPublisher("Publisher 2");
        book2.setPublishedDate(LocalDate.of(2022, 2, 2));
        book2.setEdition("Second Edition");
        book2.setCategory("Category 2");
        book2.setPages(200);
        book2.setLanguage("French");
        book2.setAvailableCopies(5);
        book2.setTotalCopies(7);
        book2.setDescription("Description 2");

        books= Arrays.asList(book, book2);

        bookDto.setId(1L);
        bookDto.setTitle("Test Title");
        bookDto.setAuthor("Test Author");
        bookDto.setIsbn("9781234567890");
        bookDto.setPublisher("Test Publisher");
        bookDto.setPublishedDate(LocalDate.of(2020, 1, 1));
        bookDto.setEdition("First Edition");
        bookDto.setCategory("Test Category");
        bookDto.setPages(300);
        bookDto.setLanguage("English");
        bookDto.setAvailableCopies(5);
        bookDto.setTotalCopies(10);
        bookDto.setDescription("Test Description");



    }


    @Test
    void testFromBookDtoToBook() {


        Book book = bookMapper.fromBookDtoToBook(bookDto);


        assertEquals(bookDto.getId(), book.getId());
        assertEquals(bookDto.getTitle(), book.getTitle());
        assertEquals(bookDto.getAuthor(), book.getAuthor());
        assertEquals(bookDto.getIsbn(), book.getIsbn());
        assertEquals(bookDto.getPublisher(), book.getPublisher());
        assertEquals(bookDto.getPublishedDate(), book.getPublishedDate());
        assertEquals(bookDto.getEdition(), book.getEdition());
        assertEquals(bookDto.getCategory(), book.getCategory());
        assertEquals(bookDto.getPages(), book.getPages());
        assertEquals(bookDto.getLanguage(), book.getLanguage());
        assertEquals(bookDto.getAvailableCopies(), book.getAvailableCopies());
        assertEquals(bookDto.getTotalCopies(), book.getTotalCopies());
        assertEquals(bookDto.getDescription(), book.getDescription());
    }

    @Test
    void testFromBookToBookDtoList() {


        List<BookDto> bookDtos = bookMapper.fromBookToBookDto(books);


        assertEquals(books.size(), bookDtos.size());



        assertEquals(book.getId(), bookDtos.get(0).getId());
        assertEquals(book.getTitle(), bookDtos.get(0).getTitle());
        assertEquals(book.getAuthor(), bookDtos.get(0).getAuthor());
        assertEquals(book.getIsbn(), bookDtos.get(0).getIsbn());
        assertEquals(book.getPublisher(), bookDtos.get(0).getPublisher());
        assertEquals(book.getPublishedDate(), bookDtos.get(0).getPublishedDate());
        assertEquals(book.getEdition(), bookDtos.get(0).getEdition());
        assertEquals(book.getCategory(), bookDtos.get(0).getCategory());
        assertEquals(book.getPages(), bookDtos.get(0).getPages());
        assertEquals(book.getLanguage(), bookDtos.get(0).getLanguage());
        assertEquals(book.getAvailableCopies(), bookDtos.get(0).getAvailableCopies());
        assertEquals(book.getTotalCopies(), bookDtos.get(0).getTotalCopies());
        assertEquals(book.getDescription(), bookDtos.get(0).getDescription());


        assertEquals(book2.getId(), bookDtos.get(1).getId());
        assertEquals(book2.getTitle(), bookDtos.get(1).getTitle());
        assertEquals(book2.getAuthor(), bookDtos.get(1).getAuthor());
        assertEquals(book2.getIsbn(), bookDtos.get(1).getIsbn());
        assertEquals(book2.getPublisher(), bookDtos.get(1).getPublisher());
        assertEquals(book2.getPublishedDate(), bookDtos.get(1).getPublishedDate());
        assertEquals(book2.getEdition(), bookDtos.get(1).getEdition());
        assertEquals(book2.getCategory(), bookDtos.get(1).getCategory());
        assertEquals(book2.getPages(), bookDtos.get(1).getPages());
        assertEquals(book2.getLanguage(), bookDtos.get(1).getLanguage());
        assertEquals(book2.getAvailableCopies(), bookDtos.get(1).getAvailableCopies());
        assertEquals(book2.getTotalCopies(), bookDtos.get(1).getTotalCopies());
        assertEquals(book2.getDescription(), bookDtos.get(1).getDescription());
    }

    @Test
    void testUpdateBookFromBookDto() {


        BookDto bookDto = new BookDto();
        bookDto.setTitle("Updated Title");
        bookDto.setAuthor("Updated Author");
        bookDto.setIsbn("9781234567890");
        bookDto.setPublisher("Updated Publisher");
        bookDto.setPublishedDate(LocalDate.of(2021, 5, 10));
        bookDto.setEdition("Updated Edition");
        bookDto.setCategory("Updated Category");
        bookDto.setPages(300);
        bookDto.setLanguage("English");
        bookDto.setAvailableCopies(5);
        bookDto.setTotalCopies(10);
        bookDto.setDescription("Updated Description");

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Original Title");
        book.setAuthor("Original Author");
        book.setIsbn("9780987654321");
        book.setPublisher("Original Publisher");
        book.setPublishedDate(LocalDate.of(2020, 1, 1));
        book.setEdition("Original Edition");
        book.setCategory("Original Category");
        book.setPages(250);
        book.setLanguage("French");
        book.setAvailableCopies(3);
        book.setTotalCopies(8);
        book.setDescription("Original Description");


        bookMapper.updateBookFromBookDto(bookDto, book);


        assertEquals(bookDto.getTitle(), book.getTitle());
        assertEquals(bookDto.getAuthor(), book.getAuthor());
        assertEquals(bookDto.getIsbn(), book.getIsbn());
        assertEquals(bookDto.getPublisher(), book.getPublisher());
        assertEquals(bookDto.getPublishedDate(), book.getPublishedDate());
        assertEquals(bookDto.getEdition(), book.getEdition());
        assertEquals(bookDto.getCategory(), book.getCategory());
        assertEquals(bookDto.getPages(), book.getPages());
        assertEquals(bookDto.getLanguage(), book.getLanguage());
        assertEquals(bookDto.getAvailableCopies(), book.getAvailableCopies());
        assertEquals(bookDto.getTotalCopies(), book.getTotalCopies());
        assertEquals(bookDto.getDescription(), book.getDescription());

        // Check that the ID is unchanged
        assertEquals(1L, book.getId());
    }


}

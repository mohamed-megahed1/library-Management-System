package com.mohamedahmed.libraryManagementSystem.service.imp;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import com.mohamedahmed.libraryManagementSystem.entities.Book;
import com.mohamedahmed.libraryManagementSystem.mapper.BookMapper;
import com.mohamedahmed.libraryManagementSystem.repository.BookRepo;
import com.mohamedahmed.libraryManagementSystem.repository.specification.BookSpecification;
import com.mohamedahmed.libraryManagementSystem.service.SearchBookService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchBookServiceImp implements SearchBookService {

    private final BookRepo bookRepo;
    private final BookMapper bookMapper;



    @Override
    public List<BookDto> SerachBook(BookDto bookDto) {
        Specification specification=new BookSpecification(bookDto);

        List<Book>books=bookRepo.findAll(specification);

        return bookMapper.fromBookToBookDto(books);
    }
}

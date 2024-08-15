package com.mohamedahmed.libraryManagementSystem.service.imp;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import com.mohamedahmed.libraryManagementSystem.entities.Book;
import com.mohamedahmed.libraryManagementSystem.exceptions.NotFoundResourceException;
import com.mohamedahmed.libraryManagementSystem.exceptions.ThisEntityAlreadyExistsException;
import com.mohamedahmed.libraryManagementSystem.mapper.BookMapper;
import com.mohamedahmed.libraryManagementSystem.repository.BookRepo;
import com.mohamedahmed.libraryManagementSystem.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImp implements BookService {

    private final BookRepo bookRepo;
    private final BookMapper bookMapper;




    @Override
    @Cacheable(value ="AllBooks" ,key = "#root.methodName")
    public List<BookDto> getAllBooks() {
        List<Book>books=bookRepo.findAll();

        return bookMapper.fromBookToBookDto(books);
    }

    @Override
    @Cacheable(value ="BookById" ,key = "#id")
    public BookDto getBookById(Long id) {
      Book book=bookRepo.findById(id).
              orElseThrow(() -> new NotFoundResourceException("This book With this id : "+id+" is not exists"));
      return bookMapper.fromBookToBookDto(book);

    }

    @Override
    @CacheEvict(value ={"BookById","AllBooks"} ,key = "#root.methodName",allEntries = true)
    public String addNewBook(BookDto bookDto) {

        if(bookRepo.existsByIsbn(bookDto.getIsbn())){
            throw new ThisEntityAlreadyExistsException("This book with this isbn code "
                    +bookDto.getIsbn()+" is already exists .");
        }else {
            Book book=bookMapper.fromBookDtoToBook(bookDto);
            bookRepo.save(book);
        }

        return "Book added Successfully";
    }

    @Override
    @CacheEvict(value ={"BookById","AllBooks"} ,key = "#root.methodName",allEntries = true)
    public BookDto updateBook(BookDto bookDto, Long id) {
        Book book=bookRepo.findById(id).
                orElseThrow(() -> new NotFoundResourceException("This book With this id : "+id+" is not exists"));

        Book newBook=bookMapper.updateBookFromBookDto(bookDto,book);
        Book updatedBook= bookRepo.save(newBook);
        return bookMapper.fromBookToBookDto(updatedBook);
    }

    @Override
    @CacheEvict(value ={"BookById","AllBooks"} ,key = "#root.methodName",allEntries = true)
    public String removeBookById(Long id) {
        if(bookRepo.existsById(id)){
            bookRepo.deleteById(id);
            return "book deleted successfully";
        }else {
            return "book is not exists";
        }


    }
}

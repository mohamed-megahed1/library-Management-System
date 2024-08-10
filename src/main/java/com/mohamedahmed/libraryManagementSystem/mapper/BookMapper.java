package com.mohamedahmed.libraryManagementSystem.mapper;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import com.mohamedahmed.libraryManagementSystem.entities.Book;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",

        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface  BookMapper {

    BookDto fromBookToBookDto(Book book);

    Book fromBookDtoToBook(BookDto bookDto);

    List<BookDto>fromBookToBookDto(List<Book>books);

    @Mapping(target = "id", ignore = true)
    Book updateBookFromBookDto(BookDto bookDto, @MappingTarget Book book);


}

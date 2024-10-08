package com.mohamedahmed.libraryManagementSystem.mapper;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import com.mohamedahmed.libraryManagementSystem.dto.PatronDto;
import com.mohamedahmed.libraryManagementSystem.entities.Book;
import com.mohamedahmed.libraryManagementSystem.entities.Patron;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",

        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface  PatronMapper {
    PatronDto fromPatronToPatronDto(Patron patron);

    Patron fromPatronDtoToPatron(PatronDto patronDto);

    List<PatronDto> fromPatronToPatronDto(List<Patron>patron);

    @Mapping(target = "id", ignore = true)
    Patron updatePatronFromPatronDto(PatronDto patronDto, @MappingTarget Patron patron);
}

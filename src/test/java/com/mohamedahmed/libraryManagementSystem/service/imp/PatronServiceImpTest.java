package com.mohamedahmed.libraryManagementSystem.service.imp;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import com.mohamedahmed.libraryManagementSystem.dto.PatronDto;
import com.mohamedahmed.libraryManagementSystem.entities.Book;
import com.mohamedahmed.libraryManagementSystem.entities.Patron;
import com.mohamedahmed.libraryManagementSystem.exceptions.NotFoundResourceException;
import com.mohamedahmed.libraryManagementSystem.mapper.PatronMapper;
import com.mohamedahmed.libraryManagementSystem.repository.PatronRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatronServiceImpTest {
    @InjectMocks
    private PatronServiceImp patronServiceImp;
    @Mock
    private PatronMapper patronMapper;
    @Mock
    private PatronRepo patronRepo;
    private static Patron patron;
    private static PatronDto patronDto;

    @BeforeEach
    void setUp() {
        patron=new Patron(1L,"username","f_name","l_name","123456789","email@email.com","+123 4567890123",null
        ,"user");
        patronDto=new PatronDto(null,"username_update","f_name_update","l_name_update","123456789_update","email@email.com_update","+123 4567890123",null
                ,"user");
    }

    @Test
    void updatePatronInfo() {
     when(patronRepo.findById(1L)).thenReturn(Optional.of(patron));
     when(patronMapper.updatePatronFromPatronDto(patronDto,patron)).thenReturn(patron);
     when(patronRepo.save(patron)).thenReturn(patron);
     when(patronMapper.fromPatronToPatronDto(patron)).thenReturn(patronDto);

     PatronDto result=patronServiceImp.updatePatronInfo(patronDto,1L);
     assertNotNull(result);

     assertEquals(result,patronDto);

    }

    @Test
    void testUpdateBook_NotFound() {
        when(patronRepo.findById(1L)).thenReturn(Optional.empty());

        NotFoundResourceException thrown = assertThrows(
                NotFoundResourceException.class,
                () -> patronServiceImp.updatePatronInfo(patronDto, 1L),
                "Expected updatePatronInfo() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("This patron with id : 1 is not exists"));
    }
}
package com.mohamedahmed.libraryManagementSystem.service.imp;

import com.mohamedahmed.libraryManagementSystem.dto.BorrowingRecordDto;
import com.mohamedahmed.libraryManagementSystem.entities.Book;
import com.mohamedahmed.libraryManagementSystem.entities.BorrowingRecord;
import com.mohamedahmed.libraryManagementSystem.entities.BorrowingStatus;
import com.mohamedahmed.libraryManagementSystem.entities.Patron;
import com.mohamedahmed.libraryManagementSystem.mapper.BorrowingRecordMapper;
import com.mohamedahmed.libraryManagementSystem.repository.BookRepo;
import com.mohamedahmed.libraryManagementSystem.repository.BorrowingRecordRepo;
import com.mohamedahmed.libraryManagementSystem.repository.PatronRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BorrowServiceImpTest {
    @InjectMocks
    private BorrowServiceImp borrowServiceImp;
    @Mock
   private  BorrowingRecordMapper borrowingRecordMapper;
    @Mock
    private  BorrowingRecordRepo borrowingRecordRepo;
    @Mock
    private  BookRepo bookRepo;
    @Mock
    private  PatronRepo patronRepo;

    private static Book book;

    private static Patron patron;

    private static BorrowingRecordDto borrowingRecordDto;
    private static BorrowingRecord borrowingRecord;
    @BeforeEach
    void setUp() {

        book = new Book(1L, "Title", "Author", "1234567890", "Publisher",
                LocalDate.of(2023, 8, 10), "Edition", "Category",
                300, "English", 10, 10, "Description");
        patron=new Patron(1L,"username","f_name","l_name","123456789","email@email.com","+123 4567890123",new HashSet<>()
                ,"user");
         borrowingRecord = new BorrowingRecord(1L, book, 1L, LocalDate.of(2023, 8, 10),
                LocalDate.of(2023, 8, 20), null, BorrowingStatus.BORROWED);
         borrowingRecordDto = new BorrowingRecordDto(1L, book, 1L, LocalDate.of(2023, 8, 10),
                LocalDate.of(2023, 8, 20), null, BorrowingStatus.BORROWED);

    }


    @Test
    void borrowBook() {
        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));
        when(patronRepo.findById(1L)).thenReturn(Optional.of(patron));
        when(borrowingRecordRepo.save(any(BorrowingRecord.class))).thenReturn(borrowingRecord);
        when(patronRepo.save(any(Patron.class))).thenReturn(patron);
        when(borrowingRecordMapper.fromBorrowingRecordToBorrowingRecordDto(ArgumentMatchers.any(BorrowingRecord.class)))
                .thenReturn(borrowingRecordDto);


        BorrowingRecordDto recordDto=borrowServiceImp.borrowBook(1L,1L);

        assertNotNull(recordDto);
        assertEquals(borrowingRecordDto,recordDto);
        assertEquals(1,patron.getBorrowingRecordSet().size());
        assertFalse(patron.getBorrowingRecordSet().isEmpty());


    }


    @Test
    void returnBook() {

    }

    @Test
    void reportLostTheBook() {
    }
}
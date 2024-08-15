package com.mohamedahmed.libraryManagementSystem.service.imp;

import com.mohamedahmed.libraryManagementSystem.dto.BookDto;
import com.mohamedahmed.libraryManagementSystem.dto.BorrowingRecordDto;
import com.mohamedahmed.libraryManagementSystem.dto.PatronDto;
import com.mohamedahmed.libraryManagementSystem.entities.Book;
import com.mohamedahmed.libraryManagementSystem.entities.BorrowingRecord;
import com.mohamedahmed.libraryManagementSystem.entities.BorrowingStatus;
import com.mohamedahmed.libraryManagementSystem.entities.Patron;
import com.mohamedahmed.libraryManagementSystem.exceptions.NotFoundResourceException;
import com.mohamedahmed.libraryManagementSystem.exceptions.ThisEntityAlreadyExistsException;
import com.mohamedahmed.libraryManagementSystem.mapper.BookMapper;
import com.mohamedahmed.libraryManagementSystem.mapper.BorrowingRecordMapper;
import com.mohamedahmed.libraryManagementSystem.mapper.PatronMapper;
import com.mohamedahmed.libraryManagementSystem.repository.BookRepo;
import com.mohamedahmed.libraryManagementSystem.repository.BorrowingRecordRepo;
import com.mohamedahmed.libraryManagementSystem.repository.PatronRepo;
import com.mohamedahmed.libraryManagementSystem.service.AdminService;
import com.mohamedahmed.libraryManagementSystem.service.BookService;
import com.mohamedahmed.libraryManagementSystem.service.BorrowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class BorrowServiceImp implements BorrowService {

    private final BorrowingRecordMapper borrowingRecordMapper;
    private final BorrowingRecordRepo borrowingRecordRepo;

    private final BookRepo bookRepo;

    private final PatronRepo patronRepo;

    @Override
    public BorrowingRecordDto borrowBook(Long bookId, Long patronId) {

        Book book=bookRepo.findById(bookId).
                orElseThrow(() -> new NotFoundResourceException("This book With this id : "+bookId+" is not exists"));
        Patron patron=patronRepo.findById(patronId).
                orElseThrow(() -> new NotFoundResourceException("This patron With this id : "+patronId+" is not exists"));
        BorrowingRecord borrowingRecord=new BorrowingRecord();
        //this if to sure that the same person can not borrow the book twice in the same time
        //or if he lost before
        if (patron.getBorrowingRecordSet() != null && !patron.getBorrowingRecordSet().isEmpty()){
            for (BorrowingRecord record: patron.getBorrowingRecordSet()) {
                if (Objects.equals(record.getBook().getId(), bookId) &&
                        (record.getStatus().equals(BorrowingStatus.BORROWED) ||
                                record.getStatus().equals(BorrowingStatus.OVERDUE)||
                                record.getStatus().equals(BorrowingStatus.LOST))){
                    throw new RuntimeException("You are not allowed to borrow this book now");
                }
            }
        }
        if (book.getAvailableCopies() >0){

           book.setAvailableCopies(book.getAvailableCopies()-1);
           borrowingRecord.setBook(book);
           borrowingRecord.setDueDate(LocalDate.now().plusDays(10));
           borrowingRecord.setReturnDate(null);
           borrowingRecord.setBorrowDate(LocalDate.now());
           borrowingRecord.setPatronId(patronId);
           borrowingRecord.setStatus(BorrowingStatus.BORROWED);

           patron.addBorrowingRecord(borrowingRecord);
           borrowingRecordRepo.save(borrowingRecord);
           patronRepo.save(patron);

       }else {
           throw new RuntimeException("This book is not available now .");
       }

        return borrowingRecordMapper.fromBorrowingRecordToBorrowingRecordDto(borrowingRecord);
    }

    @Override
    public BorrowingRecordDto returnBook(Long bookId, Long patronId) {
        Book book=bookRepo.findById(bookId).
                orElseThrow(() -> new NotFoundResourceException("This book With this id : "+bookId+" is not exists"));
        Patron patron=patronRepo.findById(patronId).
                orElseThrow(() -> new NotFoundResourceException("This patron With this id : "+patronId+" is not exists"));
        BorrowingRecordDto newRecord=new BorrowingRecordDto();
        boolean flag=false;
        for (BorrowingRecord record: patron.getBorrowingRecordSet()) {
            //The patron can not borrow the book again in the same day that he is return in
            if (Objects.equals(record.getBook().getId(), bookId) && Objects.equals(record.getPatronId(), patronId)){
                if (record.getStatus().equals(BorrowingStatus.BORROWED)||record.getStatus().equals(BorrowingStatus.OVERDUE)
                || record.getReturnDate().isBefore(LocalDate.now())){
                    book.setAvailableCopies(book.getAvailableCopies()+1);
                    record.setStatus(BorrowingStatus.RETURNED);
                    record.setReturnDate(LocalDate.now());
                    bookRepo.save(book);
                    borrowingRecordRepo.save(record);
                    patronRepo.save(patron);
                    newRecord=borrowingRecordMapper.fromBorrowingRecordToBorrowingRecordDto(record);
                    flag=true;
                    break;
                }else {
                    throw new ThisEntityAlreadyExistsException("This book already returned.");
                }
            }
        }
        if (!flag){
            throw new NotFoundResourceException("This book id or patron id is not exists on your borrowing records ");

        }

        return newRecord;
    }

    @Override
    public String reportLostTheBook(Long bookId, Long patronId) {
        Book book=bookRepo.findById(bookId).
                orElseThrow(() -> new NotFoundResourceException("This book With this id : "+bookId+" is not exists"));
        Patron patron=patronRepo.findById(patronId).
                orElseThrow(() -> new NotFoundResourceException("This patron With this id : "+patronId+" is not exists"));
        boolean flag=false;
        for (BorrowingRecord record: patron.getBorrowingRecordSet()) {
            //The patron can not borrow the book again in the same day that he is return in
            if (Objects.equals(record.getBook().getId(), bookId) && Objects.equals(record.getPatronId(), patronId)){
                if (record.getStatus().equals(BorrowingStatus.BORROWED)||
                        record.getStatus().equals(BorrowingStatus.OVERDUE)){
                    book.setAvailableCopies(book.getAvailableCopies()-1);
                    book.setTotalCopies(book.getTotalCopies()-1);
                    record.setStatus(BorrowingStatus.LOST);
                    record.setReturnDate(LocalDate.now());
                    bookRepo.save(book);
                    borrowingRecordRepo.save(record);
                    patronRepo.save(patron);
                     flag=true;
                    break;
                    }else {
                    throw new ThisEntityAlreadyExistsException("This book is already returned or lost .");
                }

            }
        }

        if (!flag){
            throw new NotFoundResourceException("This book id or patron id is not exists on your borrowing records ");

        }
        return "The repost is sent successfully";
    }


}

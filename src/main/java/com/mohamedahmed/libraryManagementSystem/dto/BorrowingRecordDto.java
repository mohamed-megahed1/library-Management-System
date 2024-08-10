package com.mohamedahmed.libraryManagementSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecordDto {

    private Long id;

    private BookDto book;


    private PatronDto patron;

    private LocalDate borrowDate;

    private LocalDate dueDate;


    private LocalDate returnDate;


    private String status;

}

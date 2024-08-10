package com.mohamedahmed.libraryManagementSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Patron_id", nullable = false)
    private Patron patron;

    @NotNull
    @PastOrPresent
    private LocalDate borrowDate;

    @NotNull
    @FutureOrPresent
    private LocalDate dueDate;

    @PastOrPresent
    private LocalDate returnDate;

    @NotBlank
    private String status;

}

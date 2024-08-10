package com.mohamedahmed.libraryManagementSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Author cannot be blank")
    private String author;
    @Column(unique = true)
    @NotBlank(message = "isbn cannot be blank")
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$")
    private String isbn;
    @NotBlank(message = "publisher cannot be blank")
    private String publisher;
    @NotNull(message = "Published date cannot be null")
    @PastOrPresent(message = "Published date must be in the past or present")
    private LocalDate publishedDate;
    @NotBlank(message = "edition cannot be blank")
    private String edition;
    @NotBlank(message = "category cannot be blank")
    private String category;
    @Positive(message = "Pages must be a positive number")
    private int pages;
    @NotBlank(message = "language cannot be blank")
    private String language;
    @Min(value = 0, message = "Available copies cannot be negative")
    private int availableCopies;
    @Min(value = 0, message = "Total copies cannot be negative")
    private int totalCopies;
    @NotEmpty(message = "description cannot be blank")
    private String description;


}

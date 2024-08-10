package com.mohamedahmed.libraryManagementSystem.entities;

public enum BorrowingStatus {
    BORROWED,
    RETURNED,
    OVERDUE, //The book has not been returned by the due date.
    LOST //The book is reported as lost by the patron.
}

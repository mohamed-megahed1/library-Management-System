package com.mohamedahmed.libraryManagementSystem.repository;

import com.mohamedahmed.libraryManagementSystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
    boolean existsByIsbn(String isbn);
}

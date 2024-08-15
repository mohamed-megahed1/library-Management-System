package com.mohamedahmed.libraryManagementSystem.repository;

import com.mohamedahmed.libraryManagementSystem.entities.Admin;
import com.mohamedahmed.libraryManagementSystem.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PatronRepo extends JpaRepository<Patron,Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String userName);

  //  Patron findByUserNameOrEmail(String userName, String email);
}

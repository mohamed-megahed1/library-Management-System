package com.mohamedahmed.libraryManagementSystem.repository;

import com.mohamedahmed.libraryManagementSystem.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AdminRepo extends JpaRepository<Admin,Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String userName);
}

package com.mohamedahmed.libraryManagementSystem.repository;

import com.mohamedahmed.libraryManagementSystem.entities.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BorrowingRecordRepo extends JpaRepository<BorrowingRecord,Long> {
}

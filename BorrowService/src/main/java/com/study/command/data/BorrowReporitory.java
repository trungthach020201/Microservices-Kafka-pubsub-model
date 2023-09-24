package com.study.command.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowReporitory extends JpaRepository<Borrow, String> {
}

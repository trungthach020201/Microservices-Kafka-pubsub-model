package com.study.command.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowCreatedEvent {
    private String borrowId;
    private String borrowDate;
    private String expiredDate;
    private String userId;
    private String bookId;
    private String bookName;
    private String fullName;
}

package com.study.command.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BorrowRequest {
    private String borrowDate;
    private String expiredDate;
    private String userId;
    private String bookId;
}

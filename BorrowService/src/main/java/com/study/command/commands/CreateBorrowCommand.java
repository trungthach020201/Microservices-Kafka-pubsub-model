package com.study.command.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

@Data
@Builder
public class CreateBorrowCommand {
    @TargetAggregateIdentifier
    private String borrowId;
    private String borrowDate;
    private String expiredDate;
    private String userId;
    private String bookId;
    private String bookName;
    private String fullName;
}

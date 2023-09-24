package com.study.command.aggregate;

import com.study.command.commands.CreateBorrowCommand;
import com.study.command.events.BorrowCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;


@Aggregate
public class BorrowAggregate {
    @AggregateIdentifier
    private String borrowId;
    private String borrowDate;
    private String expiredDate;
    private String userId;
    private String bookId;
    private String bookName;
    private String fullName;

    @CommandHandler
    public BorrowAggregate (CreateBorrowCommand createBorrowCommand) {
        BorrowCreatedEvent borrowCreatedEvent = new BorrowCreatedEvent();
        BeanUtils.copyProperties(createBorrowCommand, borrowCreatedEvent);
        AggregateLifecycle.apply(borrowCreatedEvent);
    }

    @EventSourcingHandler
    public void on (BorrowCreatedEvent borrowCreatedEvent){
        this.borrowId= borrowCreatedEvent.getBorrowId();
        this.borrowDate = borrowCreatedEvent.getBorrowDate();
        this.expiredDate = borrowCreatedEvent.getExpiredDate();
        this.bookId = borrowCreatedEvent.getBookId();
        this.userId = borrowCreatedEvent.getUserId();
    }

    public BorrowAggregate() {
    }
}

package com.study.command.aggregate;

import com.study.command.commands.CreateBookCommand;
import com.study.command.commands.DeleteAllBookCommand;
import com.study.command.commands.DeleteBookCommand;
import com.study.command.commands.UpdateBookCommand;
import com.study.command.events.BookCreatedEvent;
import com.study.command.events.BookDeletedEvent;
import com.study.command.events.BookUpdatedEvent;
import com.study.command.events.DeletedAllBookEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class BookAggregate {
    @AggregateIdentifier
    private String bookId;
    private String title;
    private String author;
    private boolean isReady;
    private int capacity;

    @CommandHandler
    public BookAggregate(CreateBookCommand createBookCommand) {
        //you can perform all the validations
        BookCreatedEvent bookCreatedEvent = new BookCreatedEvent();
        //coppy all the properties of crea to created
        BeanUtils.copyProperties(createBookCommand,bookCreatedEvent);
        //pubplish created event
        AggregateLifecycle.apply(bookCreatedEvent);
    }
    //publisher the event
    @EventSourcingHandler
    public void on(BookCreatedEvent bookCreatedEvent){
        this.bookId = bookCreatedEvent.getBookId();
        this.title = bookCreatedEvent.getTitle();
        this.author = bookCreatedEvent.getAuthor();
        this.isReady = bookCreatedEvent.isReady();
        this.capacity = bookCreatedEvent.getCapacity();
    }

    @CommandHandler
    public void handle (UpdateBookCommand updateBookCommand) {
        BookUpdatedEvent bookUpdatedEvent = new BookUpdatedEvent();
        BeanUtils.copyProperties(updateBookCommand,bookUpdatedEvent);
        AggregateLifecycle.apply(bookUpdatedEvent);
    }
    //publisher the event
    @EventSourcingHandler
    public void on(BookUpdatedEvent bookUpdatedEvent){
        this.bookId = bookUpdatedEvent.getBookId();
        this.title = bookUpdatedEvent.getTitle();
        this.author = bookUpdatedEvent.getAuthor();
        this.isReady = bookUpdatedEvent.isReady();
        this.capacity = bookUpdatedEvent.getCapacity();
    }

    @CommandHandler
    public void handle (DeleteBookCommand deleteBookCommand) {
        BookDeletedEvent bookDeletedEvent = new BookDeletedEvent();
        BeanUtils.copyProperties(deleteBookCommand, bookDeletedEvent);
        AggregateLifecycle.apply(bookDeletedEvent);
    }
    //publisher the event
    @EventSourcingHandler
    public void on(DeleteBookCommand deleteBookEvent){
        this.bookId = deleteBookEvent.getBookId();
    }

    @CommandHandler
    public void handle (DeleteAllBookCommand deleteAllBookCommand) {
        DeletedAllBookEvent bookDeletedEvent = new DeletedAllBookEvent();
        AggregateLifecycle.apply(bookDeletedEvent);
    }
    public BookAggregate() {
    }


}

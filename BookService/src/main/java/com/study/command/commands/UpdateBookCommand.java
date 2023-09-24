package com.study.command.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateBookCommand {
    @TargetAggregateIdentifier
    private String bookId;
    private String title;
    private String author;
    private boolean isReady;
    private int capacity;
}

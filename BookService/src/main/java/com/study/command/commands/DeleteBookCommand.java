package com.study.command.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DeleteBookCommand {
    @TargetAggregateIdentifier
    private String bookId;
}

package com.study.command.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdatedEvent {
    private String bookId;
    private String title;
    private String author;
    private boolean isReady;
    private int capacity;
}

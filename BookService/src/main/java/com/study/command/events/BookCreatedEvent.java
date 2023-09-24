package com.study.command.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookCreatedEvent {
    private String bookId;
    private String title;
    private String author;
    private boolean isReady;
    private int capacity;
}

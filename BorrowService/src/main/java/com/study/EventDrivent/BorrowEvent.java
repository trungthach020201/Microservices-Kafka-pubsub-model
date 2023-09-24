package com.study.EventDrivent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowEvent {
    private String eventType;
    private String bookId;
}

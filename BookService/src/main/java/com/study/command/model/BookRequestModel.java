package com.study.command.model;

import lombok.*;

@Data
@Builder
public class BookRequestModel {
    private String title;
    private String author;
    private boolean isReady;
    private int capacity;
}

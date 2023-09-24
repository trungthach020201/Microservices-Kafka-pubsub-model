package com.study.command.controller;

import com.study.command.commands.CreateBorrowCommand;
import com.study.command.request.BorrowRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/borrow")
public class BorrowCommandController {
    private final CommandGateway commandGateway;
    @PostMapping("/book")
    public ResponseEntity borrowBook (@RequestBody BorrowRequest borrowRequest){
        CreateBorrowCommand createBorrowCommand = CreateBorrowCommand.builder()
                .borrowId(UUID.randomUUID().toString())
                .borrowDate(borrowRequest.getBorrowDate())
                .expiredDate(borrowRequest.getExpiredDate())
                .bookId(borrowRequest.getBookId())
                .userId(borrowRequest.getUserId())
                .build();
        return ResponseEntity.ok(commandGateway.sendAndWait(createBorrowCommand));
    }

}

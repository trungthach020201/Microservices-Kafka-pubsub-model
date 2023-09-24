package com.study.command.controller;

import com.study.command.commands.CreateBookCommand;
import com.study.command.commands.DeleteAllBookCommand;
import com.study.command.commands.DeleteBookCommand;
import com.study.command.commands.UpdateBookCommand;
import com.study.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
public class BookCommandController {
    private final CommandGateway commandGateway;
    public BookCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public ResponseEntity addProduct (@RequestBody BookRequestModel bookRequestModel){
        CreateBookCommand createProductCommand =
                CreateBookCommand.builder()
                        .bookId(UUID.randomUUID().toString())
                        .title(bookRequestModel.getTitle())
                        .author(bookRequestModel.getAuthor())
                        .isReady(true)
                        .capacity(bookRequestModel.getCapacity())
                        .build();
//        send this command to command gateway
        return ResponseEntity.ok(commandGateway.sendAndWait(createProductCommand));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBook (@RequestBody BookRequestModel bookRequestModel, @PathVariable String id){
        UpdateBookCommand updateBookCommand =
            UpdateBookCommand.builder()
                    .bookId(id)
                    .author(bookRequestModel.getAuthor())
                    .title(bookRequestModel.getTitle())
                    .isReady(bookRequestModel.isReady())
                    .capacity(bookRequestModel.getCapacity())
                    .build();
      return  ResponseEntity.ok(commandGateway.sendAndWait(updateBookCommand));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook (@PathVariable String id){
        DeleteBookCommand deleteBookCommand =
                DeleteBookCommand.builder()
                        .bookId(id)
                        .build();
         commandGateway.sendAndWait(deleteBookCommand);
        return "Deleted book";
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(){
        DeleteAllBookCommand deleteAllBookCommand = DeleteAllBookCommand.builder().build();
        commandGateway.sendAndWait(deleteAllBookCommand);
        return "Deleted ALl Book";
    }

}

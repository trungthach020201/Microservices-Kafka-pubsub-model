package com.study.query.projection;

import com.study.command.data.Book;
import com.study.command.data.BookRepository;
import com.study.query.model.BookResponseModel;
import com.study.query.queries.GetAllBookQuery;
import com.study.query.queries.GetBookQuery;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookProjection {
    private final BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handle (GetAllBookQuery getAllBookQuery){
        List<Book> books = bookRepository.findAll();
        List<BookResponseModel> bookResponseModels =
            books.stream()
                    .map(book -> BookResponseModel.builder()
                            .title(book.getTitle())
                            .author(book.getAuthor())
                            .isReady(book.isReady())
                            .capacity(book.getCapacity())
                            .build())
                    .collect(Collectors.toList());
        return bookResponseModels;
    }

    @QueryHandler
    public BookResponseModel handle(GetBookQuery getBookQuery){
        Book book = bookRepository.findById(getBookQuery.getBookId()).orElseThrow(()-> new NotFoundException("Not Found"));
        BookResponseModel model = BookResponseModel.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .isReady(book.isReady())
                .capacity(book.getCapacity())
                .build();
        return model;
    }
}

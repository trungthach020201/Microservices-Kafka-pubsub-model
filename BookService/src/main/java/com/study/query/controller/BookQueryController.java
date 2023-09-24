package com.study.query.controller;


import com.study.query.model.BookResponseModel;
import com.study.query.queries.GetAllBookQuery;
import com.study.query.queries.GetBookQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookQueryController {

    private final QueryGateway queryGateway;

    public BookQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/all")
    public List<BookResponseModel> getAll(){
        GetAllBookQuery getBookQuery = new GetAllBookQuery();
        List<BookResponseModel> bookResponseModelList =
        queryGateway.query(getBookQuery,
                ResponseTypes.multipleInstancesOf(BookResponseModel.class)).join();
        return bookResponseModelList;
    }

    @GetMapping("/{id}")
    public BookResponseModel getById(@PathVariable String id){
        GetBookQuery getBookQuery = new GetBookQuery();
        getBookQuery.setBookId(id);
        BookResponseModel bookResponseModel =
                queryGateway.query(getBookQuery,
                        ResponseTypes.instanceOf(BookResponseModel.class)).join();
        return bookResponseModel;
    }
}

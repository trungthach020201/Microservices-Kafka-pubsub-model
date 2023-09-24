package com.study.EmployeeService.query.model;

import lombok.Builder;
import lombok.Data;

import java.awt.print.Book;
import java.util.List;

@Data
@Builder
public class UserResponseModel {
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDiscliped;
    private List<Book> books;
}

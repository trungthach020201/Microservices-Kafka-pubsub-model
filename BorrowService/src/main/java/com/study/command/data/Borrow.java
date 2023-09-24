package com.study.command.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Borrow_book")
public class Borrow {
    @Id
    private String borrowId;
    private String borrowDate;
    private String expiredDate;
    private String bookName;
    private String fullName;
    private String userId;
    private String bookId;
}

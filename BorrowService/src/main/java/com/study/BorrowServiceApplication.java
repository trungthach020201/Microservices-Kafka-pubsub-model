package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BorrowServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BorrowServiceApplication.class, args);
	}

}

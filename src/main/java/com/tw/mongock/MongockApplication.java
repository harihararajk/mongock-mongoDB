package com.tw.mongock;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMongock
@SpringBootApplication
public class MongockApplication {

	public static final String ORDER_COLLECTION_NAME = "orders";

	public static void main(String[] args) {
		SpringApplication.run(MongockApplication.class, args);
	}

}

package com.tw.mongock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;
    private String name;
    private Integer quantity;
    private Double cost;

}

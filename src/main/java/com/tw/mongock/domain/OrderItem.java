package com.tw.mongock.domain;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItem {
    private String id;
    private Integer quantity;
    private Double totalCost;
}

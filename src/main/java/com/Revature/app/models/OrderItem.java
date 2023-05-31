package com.Revature.app.models;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderItem {
    private String id;
    private String name;
    private int quantity;
    private BigDecimal price;
    private String order_id;
    private String product_id;

    public OrderItem(String name, int quantity, BigDecimal price, String order_id, String product_id) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.order_id = order_id;
        this.product_id = product_id;
    }
}

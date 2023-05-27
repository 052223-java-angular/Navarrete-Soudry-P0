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

    public OrderItem(OrderItem orderItem) {
        this.id = UUID.randomUUID().toString();
        this.name = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
        this.order_id = orderItem.getOrder_id();
        this.product_id = orderItem.getProduct_id();
    }
}

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
public class CartItem {
    private String id;
    private String name;
    private int stock;
    private int quantity;
    private BigDecimal price;
    private String cart_id;
    private String product_id;

    public CartItem(CartItem cartItem) {
        this.id = UUID.randomUUID().toString();
        this.name = cartItem.getName();
        this.stock = cartItem.getStock();
        this.quantity = cartItem.getQuantity();
        this.price = cartItem.getPrice();
        this.cart_id = cartItem.getCart_id();
        this.product_id = cartItem.getProduct_id();
    }
}

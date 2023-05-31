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
public class Cart {
    private String id;
    private BigDecimal total_cost;
    private String user_id;


    public Cart(BigDecimal total_cost, String user_id) {
        this.id = UUID.randomUUID().toString();
        this.total_cost = total_cost;
        this.user_id = user_id;
    }

    public Cart(Cart cart) {
        this.id = UUID.randomUUID().toString();
        this.total_cost = cart.getTotal_cost();
        this.user_id = cart.getUser_id();
    }
}

package com.Revature.app.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
public class Order {
    private String id;
    private OffsetDateTime created_at;
    private BigDecimal total_cost;
    private String user_id;

    public Order(OffsetDateTime created_at, BigDecimal total_cost, String user_id) {
        this.id = UUID.randomUUID().toString();
        this.created_at = created_at;
        this.total_cost = total_cost;
        this.user_id = user_id;
    }
}

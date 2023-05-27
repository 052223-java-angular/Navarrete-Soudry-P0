package com.Revature.app.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {
    private String id;
    private Integer rating;
    private String description;
    private String product_id;
    private String user_id;

    public Review(Integer rating, String description, String product_id, String user_id) {
        this.id = UUID.randomUUID().toString();
        this.rating = rating;
        this.description = description;
        this.product_id = product_id;
        this.user_id = user_id;
    }
}

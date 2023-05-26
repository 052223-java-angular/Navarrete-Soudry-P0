package com.Revature.app.models;


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
public class Review {
    private String id;
    private Integer rating;
    private String description;
    private String product_id;
    private String user_id;
}

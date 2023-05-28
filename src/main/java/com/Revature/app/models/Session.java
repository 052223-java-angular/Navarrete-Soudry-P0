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
public class Session {
    private String id;
    private String username;
    private String cart_id;


    public void setSession(User user, String cart_id)  {
        this.id = user.getId();
        this.username = user.getUsername();
        this.cart_id = cart_id;
    }

    public void setSession(String id, String name, String cart_id)  {
        this.id = id;
        this.username = name;
        this.cart_id = cart_id;
    }
}

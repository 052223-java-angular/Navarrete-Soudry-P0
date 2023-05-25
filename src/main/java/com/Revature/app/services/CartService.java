package com.Revature.app.services;

import com.Revature.app.daos.CartDAO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartService {
    private final CartDAO cartDao;
}

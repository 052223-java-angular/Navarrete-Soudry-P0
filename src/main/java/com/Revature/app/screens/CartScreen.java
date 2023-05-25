package com.Revature.app.screens;

import java.util.Scanner;

import com.Revature.app.services.CartService;
import com.Revature.app.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartScreen implements IScreen {
    private final CartService cartService;
    private final RouterService router;

    @Override
    public void start(Scanner scan) {
        System.out.println("Welcome to the cart!");
        System.out.print("Press enter to continue...");
        scan.nextLine();
    }

}

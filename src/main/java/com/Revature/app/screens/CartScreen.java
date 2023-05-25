package com.Revature.app.screens;

import java.util.Scanner;

import com.Revature.app.models.Session;
import com.Revature.app.services.CartService;
import com.Revature.app.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartScreen implements IScreen {
    private final CartService cartService;
    private final RouterService router;
    private Session session;

    @Override
    public void start(Scanner scan) {
        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the cart " + session.getUsername() +
                        "!");
                scan.nextLine();
                break;
            }
        }
    }

    /* --------------------- Helper Methods ------------------------- */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

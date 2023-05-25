package com.Revature.app.services;

import java.util.Scanner;

import com.Revature.app.daos.CartDAO;
import com.Revature.app.daos.UserDAO;
import com.Revature.app.screens.CartScreen;
import com.Revature.app.screens.HomeScreen;
import com.Revature.app.screens.LoginScreen;
import com.Revature.app.screens.RegistrationScreen;

public class RouterService {
    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                new HomeScreen(this).start(scan);
                break;
            case "/login":
                new LoginScreen(getUserService(), this).start(scan);
                break;
            case "/register":
                new RegistrationScreen(getUserService()).start(scan);
                break;
            case "/cart":
                new CartScreen(getCartService(), this).start(scan);
            default:
                break;
        }
    }

    /* ----------------- Helper Methods -------------------- */
    private UserService getUserService() {
        return new UserService(new UserDAO());
    }

    private CartService getCartService() {
        return new CartService(new CartDAO());
    }
}
package com.Revature.app.services;

import java.util.Scanner;

import com.Revature.app.daos.ProductDAO;
import com.Revature.app.daos.UserDAO;
import com.Revature.app.screens.*;

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
                new RegistrationScreen(getUserService(), this).start(scan);
                break;
            case "/mainApp":
                new MainScreen(gerProductsService()).start(scan);
                break;
            default:
                break;
        }
    }

    /* ----------------- Helper Methods -------------------- */
    private UserService getUserService() {
        return new UserService(new UserDAO());
    }

    private ProductsService gerProductsService() {
        return new ProductsService(new ProductDAO());
    }
}
package com.Revature.app.services;

import java.util.Scanner;

import com.Revature.app.daos.CartDAO;
import com.Revature.app.daos.OrderDAO;
import com.Revature.app.models.Session;
import com.Revature.app.daos.ProductDAO;
import com.Revature.app.daos.ReviewDAO;
import com.Revature.app.daos.UserDAO;
import com.Revature.app.screens.*;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RouterService {
    private Session session;

    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                new HomeScreen(this).start(scan);
                break;
            case "/login":
                new LoginScreen(getUserService(), this, session, getCartService()).start(scan);
                break;
            case "/register":
                new RegistrationScreen(getUserService(), this, session).start(scan);
                break;
            case "/mainApp":
                new MainScreen(getProductsService(), this, getCartService(), session).start(scan);
                break;
            case "/cart":
                new CartScreen(getCartService(), getOrderService(), getProductsService(), this, session).start(scan);
                break;
            case "/orders":
                new OrderScreen(getOrderService(), getReviewService(), session).start(scan);
                break;
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

    private OrderService getOrderService() {
        return new OrderService(new OrderDAO());
    }

    private ProductsService getProductsService() {
        return new ProductsService(new ProductDAO(), new ReviewDAO());
    }

    private ReviewService getReviewService() {
        return new ReviewService(new ReviewDAO());
    }
}
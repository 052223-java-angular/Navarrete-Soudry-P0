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
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RouterService {
    private Session session;

    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                getHomeScreen(scan).start(scan);
                break;
            case "/login":
                getLoginScreen(scan).start(scan);
                break;
            case "/register":
                getRegistrationScreen(scan).start(scan);
                break;
            case "/mainApp":
                getMainScreen(scan).start(scan);
                break;
            case "/cart":
                getCartScreen(scan).start(scan);
                break;
            case "/orders":
                getOrderScreen(scan).start(scan);
                break;
            default:
                break;
        }
    }

    public HomeScreen getHomeScreen(Scanner scan) {
        return new HomeScreen(this);
    }

    public LoginScreen getLoginScreen(Scanner scan) {
        return new LoginScreen(getUserService(), this, session, getCartService());
    }

    public RegistrationScreen getRegistrationScreen(Scanner scan) {
        return new RegistrationScreen(getUserService(), getCartService(), this, session);
    }

    public MainScreen getMainScreen(Scanner scan) {
        return new MainScreen(getProductsService(), this, getCartService(), getReviewService(), session);
    }

    public CartScreen getCartScreen(Scanner scan) {
        return new CartScreen(getCartService(), getOrderService(), getProductsService(), session);
    }

    public OrderScreen getOrderScreen(Scanner scan) {
        return new OrderScreen(getOrderService(), getReviewService(), session);
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
        return new ProductsService(new ProductDAO());
    }

    private ReviewService getReviewService() {
        return new ReviewService(new ReviewDAO());
    }
}
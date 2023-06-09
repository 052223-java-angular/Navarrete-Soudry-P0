package com.Revature.app.screens;

import java.math.BigDecimal;
import java.util.Scanner;

import com.Revature.app.services.UserService;
import com.Revature.app.services.CartService;
import com.Revature.app.services.RouterService;

import lombok.AllArgsConstructor;
import com.Revature.app.models.Session;
import com.Revature.app.models.User;
import com.Revature.app.models.Cart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@AllArgsConstructor
public class RegistrationScreen implements IScreen {
    public final UserService service;
    private final CartService cartService;
    public final RouterService router;
    private Session session;
    private static final Logger logger = LogManager.getLogger(HomeScreen.class);

    @Override
    public void start(Scanner scan) {
        logger.info("You have reached the home screen.");
        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the Registration");
                String username = "";
                String password = "";
                username = getUsername(scan, username);
                if (username.equals("x")) {
                    break exit;
                }
                password = getPassword(scan, username, password);
                if (password.equals("x")) {
                    break exit;
                }
                clearScreen();
                System.out.println("Please confirm your credentials:");
                System.out.println("\nUsername: " + username);
                System.out.println("Password: " + password);
                System.out.print("\nEnter (y/n): ");

                switch (scan.nextLine()) {
                    case "y":
                        User user = service.Register(username, password);
                        System.out.println("Thank you for Registering");
                        BigDecimal b1 = new BigDecimal(0);
                        Cart cart = new Cart(b1, user.getId());
                        cartService.createCart(cart);
                        session.setSession(user.getId(), username, cart.getId());
                        router.navigate("/mainApp", scan);
                        break exit;
                    case "n":

                        System.out.println("Restarting process...");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                }
            }

        }
    }

    /* ------------ Helper Methods --------------- */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private String getUsername(Scanner scan, String s1) {
        while (true) {
            System.out.print("\nEnter a username (x to cancel): ");
            s1 = scan.nextLine();

            if (s1.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!service.isValidUsername(s1)) {
                System.out.println(
                        "Your username needs to be between 5 to 10 characters long.");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            if (!service.usernameAlreadyExists(s1)) {
                System.out.println("Username is not unique!");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }
            break;
        }
        return s1;
    }

    private String getPassword(Scanner scan, String s1, String s2) {
        while (true) {
            System.out.print("\nEnter a password (x to cancel): ");
            s2 = scan.nextLine();
            if (s2.equalsIgnoreCase("x")) {
                return "x";
            }
            if (!service.isValidPassword(s2)) {
                System.out.println(
                        "Your password needs to be between 5 to 10 characters long and contain at least 1 number.");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }
            break;
        }
        return s2;
    }
}
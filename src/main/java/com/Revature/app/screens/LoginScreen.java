package com.Revature.app.screens;

import java.util.Optional;
import java.util.Scanner;

import com.Revature.app.models.Cart;
import com.Revature.app.models.Session;
import com.Revature.app.models.User;
import com.Revature.app.services.CartService;
import com.Revature.app.services.RouterService;
import com.Revature.app.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginScreen implements IScreen {
    private UserService userService;
    private CartService cartService;
    private RouterService router;
    private Session session;

    @Override
    public void start(Scanner scan) {
        String username = "";
        String password = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the Login Screen!");

                // get username
                username = getUsername(scan);

                if (username.equals("x")) {
                    break exit;
                }

                password = getPassword(scan);

                if (password.equals("x")) {
                    break exit;
                }

                // confirm user's info
                clearScreen();
                System.out.println("Please confirm your credentials:");
                System.out.println("\nUsername: " + username);
                System.out.println("Password: " + password);
                System.out.print("\nEnter (y/n): ");

                switch (scan.nextLine()) {
                    case "y":
                        Optional<User> foundUser = userService.login(username, password);
                        if (foundUser.isEmpty()) {
                            clearScreen();
                            System.out.println("Invalid username or password...");
                            System.out.print("\nPress enter to continue...");
                            scan.nextLine();
                            break;
                        }
                        // find cart
                        Cart cart = cartService.findCartByUserId(foundUser.get().getId());
                        // create session
                        session.setSession(foundUser.get(), cart.getId());
                        // successful login
                        System.out.println("\nLogin successful!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        // navigate to main screen
                        router.navigate("/mainApp", scan);
                        break exit;
                    case "n":
                        clearScreen();
                        System.out.println("Restarting process...");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                    default:
                        clearScreen();
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                }
            }
        }
    }

    /* ------------------ Helper Methods ---------------- */
    private String getUsername(Scanner scan) {
        String username = "";

        while (true) {
            System.out.print("\nEnter a username (x to cancel): ");
            username = scan.nextLine();

            if (username.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!userService.isValidUsername(username)) {
                clearScreen();
                System.out.println("Username needs to be 5-10 characters long.");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            break;
        }

        return username;
    }

    private String getPassword(Scanner scan) {
        String password = "";

        while (true) {
            System.out.print("\nEnter a password (x to cancel): ");
            password = scan.nextLine();

            if (password.equalsIgnoreCase("x")) {
                return "x";
            }

            if (password.equals("")) {
                clearScreen();
                System.out.println("Password is missing");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }

            break;
        }

        return password;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

package com.Revature.app.screens;

import java.util.Scanner;

import com.Revature.app.services.UserService;

import com.Revature.app.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistrationScreen implements IScreen {
    public final UserService service;
    public final RouterService router;

    @Override
    public void start(Scanner scan) {

        clearScreen();
        System.out.println("Thank you for selecting Registration");
        String username = "";
        String password = "";
        username = getUsername(scan, username);
        password = getPassword(scan, username, password);

        service.Register(username, password);
        clearScreen();
        System.out.println("Thank you for Registering");
        router.navigate("/mainApp", scan);
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
                System.out.println("Your password needs to be between 5 to 10 characters long and contain at least 1 number.");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }
            break;
        }
        return s2;
    }
}
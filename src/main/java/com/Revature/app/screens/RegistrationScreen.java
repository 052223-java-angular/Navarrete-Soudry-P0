package com.Revature.app.screens;

import java.util.Scanner;

import com.Revature.app.services.UserService;

public class RegistrationScreen implements IScreen {
    public final UserService service;

    public RegistrationScreen(UserService service) {
        this.service = service;
    }

    @Override
    public void start(Scanner scan) {

        clearScreen();
        System.out.println("Thank you for selecting Registration");
        String username = "";
        String password = "";
        username = getUsername(scan, username);
        password = getPassword(scan, username, password);

        service.register(username, password);
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
                        "Your username needs to be between 4 and 10 characters, and it must contain at least 1 number at least one of the listed symbols and can only contain letters, numbers, and the following characters. !@#$%^&*_ .");
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
            if (!service.isValidPassword(s1)) {
                System.out.println("Password must be 5-10 characters long and contain at least number in it");
                System.out.print("\nPress enter to continue...");
                scan.nextLine();
                continue;
            }
            break;
        }
        return s2;
    }
}
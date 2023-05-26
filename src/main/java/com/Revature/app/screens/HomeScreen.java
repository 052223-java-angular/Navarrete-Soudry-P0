package com.Revature.app.screens;

import java.util.Scanner;

import com.Revature.app.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HomeScreen implements IScreen {
    private final RouterService router;

    @Override
    public void start(Scanner scan) {
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the app!");
                System.out.println("\n[1] Login screen");
                System.out.println("[2] register screen");
                System.out.println("[3] fast-track-tomain");
                System.out.println("[x] Exit");

                System.out.print("\nEnter: ");
                input = scan.nextLine();
                clearScreen();
                switch (input.toLowerCase()) {
                    case "1":
                        router.navigate("/login", scan);
                        break;
                    case "2":
                        router.navigate("/register", scan);
                        break;
                    case "3":
                        router.navigate("/mainApp", scan);
                        break;
                    case "x":
                        System.out.println("\nGoodbye!");
                        break exit;
                    default:
                        // clearScreen();
                        System.out.println("Invalid option!");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        break;
                }
            }
        }
    }

    /*
     * ------------------------ Helper methods ------------------------------
     */

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

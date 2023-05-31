package com.Revature.app.screens;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.Revature.app.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HomeScreen implements IScreen {
    private final RouterService router;
    private static final Logger logger = LogManager.getLogger(HomeScreen.class);

    @Override
    public void start(Scanner scan) {
        logger.info("You have reached the home screen.");
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the app!");
                System.out.println("\n[1] Login screen");
                System.out.println("[2] Register screen");
                System.out.println("[x] Exit");

                System.out.print("\nEnter: ");
                input = scan.nextLine();
                clearScreen();
                switch (input.toLowerCase()) {
                    case "1":
                        router.navigate("/login", scan);
                        logger.info("You have returned to the homescreen from login.");
                        break;
                    case "2":
                        router.navigate("/register", scan);
                        logger.info("You have returned to the register from login.");
                        break;
                    case "x":
                        System.out.println("\nGoodbye!");
                        
                        break exit;
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

    /*
     * ------------------------ Helper methods ------------------------------
     */

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

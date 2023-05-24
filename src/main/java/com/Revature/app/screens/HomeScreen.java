package com.Revature.app.screens;

import java.util.Scanner;

public class HomeScreen implements IScreen {

    @Override
    public void start(Scanner scan) {
        String input = "";

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to app");
                System.out.println("\n[1] Login screen");
                System.out.println("[2] Register screen");
                System.out.println("[x] Exit");

                System.out.print("\nEnter: ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                        break;
                    case "2":
                        break;
                    case "x":
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

    /* ------------ Helper Methods --------------- */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

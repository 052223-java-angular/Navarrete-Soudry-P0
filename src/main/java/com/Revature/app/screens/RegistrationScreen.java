package com.Revature.app.screens;
import java.util.Scanner;

import com.Revature.app.services.RegistrationService;

public class RegistrationScreen implements IScreen {

    public final RegistrationService service;

    public RegistrationScreen(RegistrationService service) {
        this.service = service;
    }

    @Override
    public void start(Scanner scan) {
        // TODO Auto-generated method stub
        clearScreen();
        System.out.println("Thank you for selecting Registration");
        String username = "";
        String password = "";
        username = getUsername(scan, username);
        password = getPassword(scan, password);
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
                    System.out.println("Username needs to be 8-20 characters long.");
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

        private String getPassword(Scanner scan,  String s2) {
            while (true) {
                System.out.print("\nEnter a password (x to cancel): ");
                s2 = scan.nextLine();
                if (s2.equalsIgnoreCase("x")) {
                    return "x";
                }
                break;
            }
            return s2;
        }
    }
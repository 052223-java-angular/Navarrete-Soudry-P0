package com.Revature.app.Registration;
import java.util.Scanner;

public class RegistrationScreen {

    public final RegistrationService service;

    public RegistrationScreen(RegistrationService service) {
        this.service = service;
    }

    public void testLogic(Scanner scan) {

        String username = "";
        String password = "";

        System.out.println("Registation logic Test");

        username = getUsername(scan, username);

        password = getPassword(scan, password);

        System.out.println("Your username and password are " + username + " " + password);

    }


    // Helper methods to decouple code.

    private String getUsername(Scanner scan, String s1) {
      

        while (true) {
            System.out.print("\nEnter a username (x to cancel): ");
            s1 = scan.nextLine();

            if (s1.equalsIgnoreCase("x")) {
                return "x";
            }

            if (!service.isValidUsername(s1)) {
                
            }

            // if (!userService.isValidUsername(username)) {
               
            //     System.out.println("Username needs to be 8-20 characters long.");
            //     System.out.print("\nPress enter to continue...");
            //     scan.nextLine();
            //     continue;
            // }

            // if (!userService.isUniqueUsername(username)) {
            
            //     System.out.println("Username is not unique!");
            //     System.out.print("\nPress enter to continue...");
            //     scan.nextLine();
            //     continue;
            // }

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
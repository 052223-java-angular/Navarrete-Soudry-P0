package com.Revature.app.screens;
import com.Revature.app.services.ProductsService;

import java.util.Scanner;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MainScreen implements IScreen {
    private final ProductsService product;

    @Override
    public void start(Scanner scan) {
    
        System.out.println("Welcome to the main application!");
        System.out.println("\n[1] Press 1 to get a catalogue of all available items");
        System.out.println("[2] Press 2 to search for items within a price range.");
        System.out.println("[3] Press 3 to search for items by category.");
     
        System.out.print("\nEnter: ");
        String input = scan.nextLine();
        clearScreen();
        switch (input.toLowerCase()) {
            case "1":
                product.getAll();
                break;
            case "2":
            System.out.println("What is the minimum price you are looking for.");
            Float f1 = scan.nextFloat();

            System.out.println("What is the maximum price you are looking for.");
           
            Float f2 = scan.nextFloat();
            System.out.println(f2);
               product.getByPrice(f1, f2);
                break;
            case "3":
            System.out.println("Please type 1 for category, or type 2 for name");
            String input2 = scan.nextLine();
            switch (input2.toLowerCase()) {
                case "1":
                System.out.println("Please input your category");
                String input3 = scan.nextLine();
                System.out.println("Are you seeing this?");
                product.getByCategory(input3);
                break;
                case "2":
                System.out.println("Please input your category");
                String input4 = scan.nextLine();
                product.getByName(input4);
                break;
                default:
                System.out.println("Non valid input");
            }
        }
    }


    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
} 
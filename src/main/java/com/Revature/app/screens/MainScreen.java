package com.Revature.app.screens;

import com.Revature.app.services.ProductsService;
import com.Revature.app.services.RouterService;

import java.util.Scanner;

import lombok.AllArgsConstructor;
import java.util.Optional;

import java.util.List;
import java.util.ArrayList;
import com.Revature.app.models.Product;
import com.Revature.app.models.Review;

@AllArgsConstructor
public class MainScreen implements IScreen {
    private final ProductsService product;
    private final RouterService router;

    @Override
    public void start(Scanner scan) {
        clearScreen();
        System.out.println("Welcome to the main application!");
        System.out.println("\n[1] Press 1 to get a catalogue of all available items");
        System.out.println("[2] Press 2 to search for items within a price range.");
        System.out.println("[3] Press 3 to search for items by category.");
        System.out.println("[4] Press 4 to go to cart");
        System.out.println("[5] Log out.");

        System.out.print("\nEnter: ");
        String input = scan.nextLine();
        clearScreen();

        switch (input.toLowerCase()) {
            case "1":
            Optional<List<Product>> p1 = product.getAll();
            if (p1.isPresent()) {
                learnMoreAboutProducts(scan, p1);
            }
                break;
            case "2":
                System.out.println("What is the minimum price you are looking for?");
                Float f1 = scan.nextFloat();
                System.out.println("What is the maximum price you are looking for?");
                Float f2 = scan.nextFloat();
                Optional<List<Product>> pricedProduct = product.getByPrice(f1, f2);
                if (pricedProduct.isPresent()) {
                    learnMoreAboutProducts(scan, pricedProduct);
                }
                // reset scan
                scan.nextLine();
                break;
            case "3":
                System.out.println("Please type 1 for category, or type 2 for name");
                String input2 = scan.nextLine();
                switch (input2.toLowerCase()) {
                    case "1":
                        System.out.println("Please input your category");
                        String input3 = scan.nextLine();
                        Optional<List<Product>> categoriedProduct = product.getByCategory(input3);
                        if (categoriedProduct.isPresent()) {
                            learnMoreAboutProducts(scan, categoriedProduct);
                        }
                        break;
                    case "2":
                        System.out.println("Please input your name");
                        String input4 = scan.nextLine();
                        Optional<List<Product>> namedProduct = product.getByName(input4);
                        if (namedProduct.isPresent()) {
                            learnMoreAboutProducts(scan, namedProduct);
                        }
                        break;
                    default:
                        System.out.println("Non valid input");
                }
                break;
            case "4":
                router.navigate("/cart", scan);
                break;
            case "5":
                router.navigate("/home", scan);
                break;
        }
        System.out.println("Please hit enter to continue");
        String clearingVariable = scan.nextLine();
        if (clearingVariable.equals("")) {
            router.navigate("/mainApp", scan);
        }
    }

    private void learnMoreAboutProducts(Scanner scan, Optional<List<Product>> p) {
        System.out.println("do you see this");
        List<Product> P = new ArrayList<>(p.get());
        // P = new ArrayList<>();
        for (int i = 0; i < P.size(); i++) {
            // int v = i + 1;
            System.out.println("Press " + i + " to get more info on " + P.get(i).getName());
        }
     
        Integer choice = scan.nextInt();
        printOutFinerDetails(choice, P);
        // reset code
        // scan.nextLine();
    }
    
    private void printOutFinerDetails(Integer i, List<Product> P) {
        String name = P.get(i).getName();
        Optional<List<Review>> reviews = product.getReview(name);

        System.out.println(P.get(i).getName());
        System.out.println(P.get(i).getDescription());

        if (reviews.isPresent()) {
            List<Review> r2 = new ArrayList<>(reviews.get());
            System.out.println("Rating");
            for (Review rr : r2) {
                System.out.print(rr.getRating() + " ");
                System.out.println(rr.getDescription());
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
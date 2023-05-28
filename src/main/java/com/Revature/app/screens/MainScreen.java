package com.Revature.app.screens;

import com.Revature.app.services.CartService;
import com.Revature.app.services.ProductsService;
import com.Revature.app.services.RouterService;

import java.util.Scanner;

import lombok.AllArgsConstructor;
import java.util.Optional;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.Revature.app.models.CartItem;
import com.Revature.app.models.Product;
import com.Revature.app.models.Review;
import com.Revature.app.models.Session;

@AllArgsConstructor
public class MainScreen implements IScreen {
    private final ProductsService product;
    private final RouterService router;
    private final CartService cart;
    private Session session;

    @Override
    public void start(Scanner scan) {
<
        exit: {
        while (true) {
            clearScreen();
            System.out.println("Welcome to the main application!");
            System.out.println("\n[1] Press 1 to get a catalogue of all available items");
            System.out.println("[2] Press 2 to search for items within a price range.");
            System.out.println("[3] Press 3 to search for items by category.");
            System.out.println("[4] Press 4 to go to cart");
            System.out.println("[5] Press 5 to go to orders");
            System.out.println("[x] Log out.");
    
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
                    continue;
                case "5":
                    router.navigate("/orders", scan);
                    continue;
                case "x":
                    break exit;
            }
=
        }
      
        }
    }
       /* ------------------ Helper Methods ---------------- */

    private void learnMoreAboutProducts(Scanner scan, Optional<List<Product>> p) {
        clearScreen();
        List<Product> P = new ArrayList<>(p.get());
        while (true) {

            listOutProduct(P);


            System.out.println("YOUR PRODUCTS!!!!!");
            System.out.print("Name ");
            System.out.print("Price ");
            System.out.print("Stock ");
            System.out.println("Category ");
            for (Product product : P) {

                System.out.print(product.getName() + " ");
                System.out.print(product.getPrice() + " ");
                System.out.print(product.getStock() + " ");
                System.out.println(product.getCategory_id() + " ");
            }

            System.out.println("");

            for (int i = 0; i < P.size(); i++) {
                // int v = i + 1;
                System.out.println("Press " + i + " to get more info on " + P.get(i).getName());
            }

            Integer value = P.size() + 1;
            System.out.println("Press " + value + " to go back to the main menu");
            Integer choice = scan.nextInt();
            clearScreen();
            printOutFinerDetails(scan, choice, P);
            if (choice > P.size()) {
                System.out.println("This is inside " + choice);
                break;
            }
        }
    }

    private void printOutFinerDetails(Scanner scan, Integer counter, List<Product> P) {
        if (counter.equals(P.size())) {
            for (int i = 0; i < P.size(); i++) {
                System.out.println("Press " + i + " to buy " + P.get(i).getName());
            }
            Integer b = scan.nextInt();
            buyingMethod(scan, P.get(b));
        } else if (counter < P.size()) {
            String name = P.get(counter).getName();
            Optional<List<Review>> reviews = product.getReview(name);

            System.out.println(P.get(counter).getName());
            System.out.println(P.get(counter).getDescription());

            if (reviews.isPresent()) {
                List<Review> r2 = new ArrayList<>(reviews.get());
                System.out.println("Rating Rating Description");
                for (Review rr : r2) {
                    System.out.print(rr.getRating() + " ");
                    System.out.println(rr.getDescription());
                }
            }
            System.out.println("");
            System.out.println("Would you like to purchase this item? y/N");
            scan.nextLine();
            String value = scan.nextLine();
            switch (value) {
                case "y":
                    buyingMethod(scan, P.get(counter));
                    break;
                default:
                    clearScreen();
                    break;
            }
        }
    }

    public void listOutProduct(List<Product> P) {
        if (!new ArrayList<>(P).isEmpty()) {
                System.out.println("The Products we have for sail.");
                System.out.print("Name ");
                System.out.print("Price ");
                System.out.print("Stock ");
                System.out.println("Category ");
                for (Product product : P) {
                    System.out.print(product.getName() + " " );
                    System.out.print(product.getPrice() + " " );
                    System.out.print(product.getStock() + " " );
                    System.out.println(product.getCategory_id() + " " );
                } 
            System.out.println("");
            for (int i = 0; i < P.size(); i++) {
                System.out.println("Press " + i + " to get more info on " + P.get(i).getName());
            }
            System.out.println("Press " + P.size() + " to purchase one of thise items.");
        } else {
            System.out.println("I'm sorry but there no products with the criteria you listed.");
        }
    }
    
    public void buyingMethod(Scanner scan, Product P) {

        while (true) {
            System.out.println("How many would you like to buy.");
            int numberOfProduct = scan.nextInt();
            if (numberOfProduct > P.getStock()) {
                System.out.println("You can not buy more than the current stock. We only have " + P.getStock() + " available.");
            } else {
                BigDecimal totalCalculation = new BigDecimal(String.valueOf(numberOfProduct)).multiply(P.getPrice());
                CartItem cartItem = new CartItem(P.getName(), P.getStock(), numberOfProduct, totalCalculation, session.getCart_id(), P.getId());
                cart.createCartItem(cartItem);
                System.out.println("Thank you for submitting your cartItem");
                break;
            }
        }

        System.out.println("This is the code when you buyin.");
        // buying logic

        System.out.println("Please hit enter");
        scan.nextLine();
    }
 
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
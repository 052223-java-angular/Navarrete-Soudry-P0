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

import com.Revature.app.models.Cart;
import com.Revature.app.models.CartItem;
import com.Revature.app.models.Product;
import com.Revature.app.models.Review;
import com.Revature.app.models.Session;

import com.Revature.app.services.ReviewService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@AllArgsConstructor
public class MainScreen implements IScreen {
    private final ProductsService product;
    private final RouterService router;
    private final CartService cart;
    private final ReviewService review;
    private Session session;
    private static final Logger logger = LogManager.getLogger(HomeScreen.class);

    @Override
    public void start(Scanner scan) {
        logger.info("You have reached the main screen.");
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
                        logger.info("User viewed all available items.");
                        if (p1.isPresent()) {
                            learnMoreAboutProducts(scan, p1);
                        }
                        clearScreen();
                        break;
                    case "2":
                        System.out.println("What is the minimum price you are looking for?");
                        BigDecimal f1 = scan.nextBigDecimal();
                        logger.info("User set minimum price for item.");
                        System.out.println("What is the maximum price you are looking for?");
                        BigDecimal f2 = scan.nextBigDecimal();
                        logger.info("User set maximum price for item.");
                        Optional<List<Product>> pricedProduct = product.getByPrice(f1, f2);
                        if (pricedProduct.isPresent()) {
                            logger.info("User viewed product within price range.");
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
                                String input3 = scan.nextLine().toLowerCase();

                                String finalInput = fixInput(input3);
                                logger.info("User specified product category.");
                                Optional<List<Product>> categoriedProduct = product.getByCategory(finalInput);
                                if (categoriedProduct.isPresent()) {
                                    learnMoreAboutProducts(scan, categoriedProduct);
                                }
                                break;
                            case "2":
                                System.out.println("Please input your name");
                                String input4 = scan.nextLine().toLowerCase();
                                String finalInput2 = fixInput(input4);
                                logger.info("User specified product name.");
                                Optional<List<Product>> namedProduct = product.getByName(finalInput2);
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
            }
        }
    }
    /* ------------------ Helper Methods ---------------- */

    // private void learnMoreAboutProducts(Scanner scan, Optional<List<Product>> p) {
    //     clearScreen();
    //     List<Product> P = new ArrayList<>(p.get());
    //     while (true) {
    //         clearScreen();
    //         listOutProduct(P);
    //         Integer value = P.size() + 1;
    //         System.out.println("");
    //         System.out.println("Press " + value + " to go back to the main menu");
    //         Integer choice = scan.nextInt();
    //         clearScreen();
    //         if (choice.equals(value)) {
    //             break;
    //         }
    //         printOutFinerDetails(scan, choice, P);
    //     }
    // }


    private void learnMoreAboutProducts(Scanner scan, Optional<List<Product>> p) {
        clearScreen();
        if (p.isPresent() && !p.get().isEmpty()) {
            List<Product> P = p.get();
            while (true) {
                clearScreen();
                listOutProduct(P);
                int value = P.size();
                System.out.println("");
                System.out.println("Press " + value + " to go back to the main menu");
                int choice = scan.nextInt();
                clearScreen();
                if (choice == value) {
                    break;
                }
                printOutFinerDetails(scan, choice, P);
            }
        } else {
            System.out.println("No products available.");
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
            Optional<List<Review>> reviews = review.getReview(name);

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
            logger.info("User viewes ratings.");
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
          clearScreen();
        if (!new ArrayList<>(P).isEmpty()) {
        
            System.out.println("The Products we have for sale.");
            System.out.println("");
            System.out.print("Name ");
            System.out.print("Price ");
            System.out.print("Stock ");
            System.out.println("");
            System.out.println("");
         
            for (Product product : P) {
                System.out.print(product.getName() + " ");
                System.out.print(product.getPrice() + " ");
                System.out.println(product.getStock() + " ");
    
            }
            System.out.println("");
            for (int i = 0; i < P.size(); i++) {
                System.out.println("Press " + i + " to get more info on " + P.get(i).getName());
            }
            System.out.println("");
            
            System.out.println("Press " + P.size() + " to purchase one of thise items.");
        } else {
            System.out.println("I'm sorry but there no products with the criteria you listed.");
        }
    }

    public void buyingMethod(Scanner scan, Product P) {

        while (true) {
            System.out.println("How many would you like to buy.");
            int numberOfProduct = scan.nextInt();

            logger.info("User specified how much of product they wished to buy.");
            if (numberOfProduct > P.getStock()) {
                System.out.println(
                        "You can not buy more than the current stock. We only have " + P.getStock() + " available.");
            } else {
                BigDecimal totalCalculation = new BigDecimal(String.valueOf(numberOfProduct)).multiply(P.getPrice());
                CartItem cartItem = new CartItem(P.getName(), P.getStock(), numberOfProduct, totalCalculation,
                        session.getCart_id(), P.getId());
                cart.createCartItem(cartItem);
                Cart foundCart = cart.findCartByCartId(session.getCart_id());
                foundCart.setTotal_cost(foundCart.getTotal_cost().add(totalCalculation));
                cart.updateCart(foundCart);
                logger.info("User added product to a cart.");
                System.out.println("You have added " + numberOfProduct + " " + P.getName() + " to your cart.");
                break;
            }
        }
        scan.nextLine();
        System.out.println("Please hit enter to be return to your previous item query.");
        scan.nextLine();
        clearScreen();
    }

    private String fixInput(String input3) {
        String part1 = input3.substring(0, 1).toUpperCase(); // "c"
        String part2 = input3.substring(1);
        return part1 + part2;

    }
    
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
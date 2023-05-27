package com.Revature.app.screens;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.Revature.app.models.Order;
import com.Revature.app.models.OrderItem;
import com.Revature.app.models.Session;
import com.Revature.app.services.OrderService;
import com.Revature.app.services.ReviewService;
import com.Revature.app.services.RouterService;

import com.Revature.app.models.Review;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderScreen implements IScreen {
    private final OrderService orderService;
    private final RouterService router;
    private Session session;
    private final ReviewService reviewService;

    @Override
    public void start(Scanner scan) {
        List<Order> orders = orderService.getAllOrdersByUserId(session.getId());
        String orderOption = "";
        String orderItemOption = "";
        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to your orders " + session.getUsername());

                // show orders
                showOrders(orders);

                // options
                System.out.println("\n[1] View order");
                System.out.println("[x] Go back");
                System.out.print("\nEnter option: ");

                // get option
                switch (scan.nextLine()) {
                    case "1":
                        while (true) {
                            clearScreen();
                            // get order option
                            orderOption = getOrderOption(orders, scan);
                            if (orderOption.equals("x")) {
                                break;
                            }

                            List<OrderItem> orderItems = orderService
                                    .getAllOrderItemsByOrderId(
                                            orders.get(Integer.parseInt(orderOption) - 1).getId());
                            while (true) {
                                clearScreen();

                                // show order items
                                showOrderItems("Viewing items...", orderItems);

                                // options
                                System.out.println("\n[1] Leave a review");
                                System.out.println("[x] Go back");
                                System.out.print("\nEnter option: ");

                                // get option
                                switch (scan.nextLine()) {
                                    case "1":
                                        // get order item option
                                        orderItemOption = getOrderItemOption(orderItems, scan);
                                        if (orderItemOption.equals("x")) {
                                            continue;
                                        }

                                        // leave review
                                        leaveReview(orderItems.get(Integer.parseInt(orderItemOption) - 1), scan);
                                        continue;
                                    case "x":
                                        break;
                                    default:
                                        continue;
                                }
                                break;
                            }
                        }
                        break;
                    case "x":
                        break exit;
                    default:
                        break;
                }
            }
        }
    }

    /* --------------------- Helper Methods --------------------------- */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void showOrders(List<Order> orders) {
        for (Order order : orders) {
            System.out.println("\nCreated at: " + order.getCreated_at() + " - Total: $" + order.getTotal_cost());
        }
    }

    private void showOrderOptions(List<Order> orders) {
        int counter = 1;
        for (Order order : orders) {
            System.out.println(
                    "\n[" + counter + "] Created at: " + order.getCreated_at() + " - Total: $" + order.getTotal_cost());
            counter += 1;
        }
    }

    private String getOrderOption(List<Order> orders, Scanner scan) {
        String orderOption = "";
        while (true) {
            clearScreen();
            System.out.println("Choosing order...");
            // show order options
            showOrderOptions(orders);

            // get option
            System.out.print("\nChoose option (x to cancel): ");
            orderOption = scan.nextLine();

            if (orderOption.equalsIgnoreCase("x")) {
                return "x";
            } else if (!isValidNumber(orderOption) || Integer.parseInt(orderOption) < 1
                    || Integer.parseInt(orderOption) > orders.size()) {
                clearScreen();
                System.out.println("Input is invalid: must be a number between 1 and " + orders.size());
                System.out.print("\nEnter to continue...");
                scan.nextLine();
                continue;
            }

            return orderOption;
        }
    }

    private String getOrderItemOption(List<OrderItem> orderItems, Scanner scan) {
        String orderItemOption = "";
        while (true) {
            clearScreen();
            System.out.println("Choosing item...");
            // show order options
            showOrderItemOptions(orderItems);

            // get option
            System.out.print("\nChoose option (x to cancel): ");
            orderItemOption = scan.nextLine();

            if (orderItemOption.equalsIgnoreCase("x")) {
                return "x";
            } else if (!isValidNumber(orderItemOption) || Integer.parseInt(orderItemOption) < 1
                    || Integer.parseInt(orderItemOption) > orderItems.size()) {
                clearScreen();
                System.out.println("Input is invalid: must be a number between 1 and " + orderItems.size());
                System.out.print("\nEnter to continue...");
                scan.nextLine();
                continue;
            }

            return orderItemOption;
        }
    }

    private void showOrderItems(String title, List<OrderItem> orderItems) {
        System.out.println(title);
        for (OrderItem orderItem : orderItems) {
            System.out.println("\n" + orderItem.getName() + " - Price: $" + orderItem.getPrice()
                    + " Quantity: " + orderItem.getQuantity());
        }
    }

    private void showOrderItemOptions(List<OrderItem> orderItems) {
        int counter = 1;
        for (OrderItem orderItem : orderItems) {
            System.out.println("\n[" + counter + "] " + orderItem.getName() + " - Price: $" + orderItem.getPrice()
                    + " Quantity: " + orderItem.getQuantity());
            counter += 1;
        }
    }

    private void leaveReview(OrderItem orderItem, Scanner scan) {
        String input = "";
        while (true) {
            clearScreen();
            System.out.println("Welcome to Review screen");

            System.out.println("\nWhat out of 5 would you rate this product");
            System.out.print("\nEnter rating: ");
            input = scan.nextLine();
            if (!isValidNumber(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) > 5) {
                clearScreen();
                System.out.println("Input is invalid: must be a number between 0 and 5");
                System.out.print("\nEnter to continue...");
                scan.nextLine();
                continue;
            }
            int value = Integer.parseInt(input);

            System.out.println("\nPlease describe your opinion of the product");
            System.out.print("\nEnter description: ");
            String value2 = scan.nextLine();

            System.out.println("\nPlease confirm your information is correct: " + orderItem.getName());
            System.out.println("\nRating: " + value);
            System.out.println("Description: " + value2);
            System.out.print("\nEnter (y/n): ");
            String value3 = scan.nextLine().toLowerCase();
            if (value3.equals("y")) {

                reviewService.sendAReview(new Review(value, value2, orderItem.getProduct_id(), session.getId()));
                clearScreen();
                System.out.println("Your review has been submitted!");
                System.out.print("\nPlease hit enter to return to viewing your order...");
                scan.nextLine();
            } else {
                clearScreen();
                System.out.println("Scrapping your previous rating. Please hit enter and try again...");
                scan.nextLine();
                continue;
            }
            break;
        }
    }

    private boolean isValidNumber(String possibleNum) {
        if (possibleNum.length() == 0 || !Pattern.matches("[0-9]+", possibleNum)) {
            return false;
        }
        return true;
    }

}

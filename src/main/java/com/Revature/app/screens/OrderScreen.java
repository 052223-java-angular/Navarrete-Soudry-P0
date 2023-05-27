package com.Revature.app.screens;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.Revature.app.models.Order;
import com.Revature.app.models.OrderItem;
import com.Revature.app.models.Session;
import com.Revature.app.services.OrderService;
import com.Revature.app.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderScreen implements IScreen {
    private final OrderService orderService;
    private final RouterService router;
    private Session session;

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
                            orderOption = getOrderOption("Choosing order...", orders, scan);
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
                                        orderItemOption = getOrderItemOption("Choosing item...", orderItems, scan);
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

    private String getOrderOption(String title, List<Order> orders, Scanner scan) {
        String orderOption = "";
        while (true) {
            clearScreen();
            System.out.println(title);
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
                System.out.println("Must be a number between 1 and " + orders.size());
                System.out.print("\nEnter to continue...");
                scan.nextLine();
                continue;
            }

            return orderOption;
        }
    }

    private String getOrderItemOption(String title, List<OrderItem> orderItems, Scanner scan) {
        String orderItemOption = "";
        while (true) {
            clearScreen();
            System.out.println(title);
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
                System.out.println("Must be a number between 1 and " + orderItems.size());
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
        /* TODO */
        while (true) {
            clearScreen();
            System.out.println("welcome to review");
            System.out.print("\nEnter to continue...");
            scan.nextLine();
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

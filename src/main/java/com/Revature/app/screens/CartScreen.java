package com.Revature.app.screens;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.Revature.app.models.Cart;
import com.Revature.app.models.CartItem;
import com.Revature.app.models.Session;
import com.Revature.app.services.CartService;
import com.Revature.app.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartScreen implements IScreen {
    private final CartService cartService;
    private final RouterService router;
    private Session session;

    @Override
    public void start(Scanner scan) {
        // find cart
        Optional<Cart> cartFound = cartService.findCartByUserId(session.getId());
        exit: {
            // empty cart screen
            if (cartFound.isEmpty()) {
                cartIsEmptyScreen(scan);
                break exit;
            }

            // cart screen
            // get cart items
            List<CartItem> cartItems = cartService.findAllCartItemsByCartId(cartFound.get().getId());
            while (true) {
                clearScreen();
                // intro
                System.out.println("Welcome to the cart " + session.getUsername() +
                        "!");
                // display products in cart
                showCartItems(cartItems);
                // options
                System.out.println("\n[1] Remove item");
                System.out.println("[2] Change item quantity");
                System.out.println("[3] Proceed to payment");
                System.out.println("[x] Go back");

                System.out.print("\nChoose an option: ");
                String itemOption = "";
                String itemQuantity = "";
                switch (scan.nextLine()) {
                    case "1":
                        while (true) {
                            // edit cart
                            // choose which item to edit
                            // option is validated to be an int and in range of cartItems
                            itemOption = getItemOption("Removing item", cartItems, scan);
                            if (itemOption.equals("x")) {
                                break;
                            }

                            // chosen cart item
                            CartItem cartItem = cartItems.get(Integer.parseInt(itemOption) - 1);
                            // delete cart item
                            cartService.deleteCartItem(cartItem.getId());
                            cartItems.remove(Integer.parseInt(itemOption) - 1);

                            // success removal
                            clearScreen();
                            System.out.println("Removal successful");
                            System.out.print("\nPress enter to continue...");
                            scan.nextLine();

                            // delete cart
                            if (cartItems.isEmpty()) {
                                cartService.deleteCart(cartFound.get().getId());
                                cartFound = Optional.empty();
                                // show empty cart screen
                                cartIsEmptyScreen(scan);
                                // leave cart screen
                                break exit;
                            }
                            break;
                        }
                        break;
                    case "2":
                        while (true) {

                            // edit cart
                            // choose item to edit
                            itemOption = getItemOption("Changing quantity...", cartItems, scan);
                            if (itemOption.equals("x")) {
                                break;
                            }

                            // chosen cart item
                            CartItem cartItem = cartItems.get(Integer.parseInt(itemOption) - 1);
                            // get new quantity
                            itemQuantity = getQuantity(cartItem.getAmount(), cartItem.getStock(), scan);
                            if (itemQuantity.equals("x")) {
                                continue;
                            }

                            int quantity = Integer.parseInt(itemQuantity);
                            // delete item
                            if (quantity == 0) {
                                // delete cart item
                                cartService.deleteCartItem(cartItem.getId());
                                cartItems.remove(Integer.parseInt(itemOption) - 1);

                                // success removal
                                clearScreen();
                                System.out.println("Removal successful");
                                System.out.print("\nPress enter to continue...");
                                scan.nextLine();

                                // delete cart
                                if (cartItems.isEmpty()) {
                                    cartService.deleteCart(cartFound.get().getId());
                                    cartFound = Optional.empty();
                                    // show empty cart screen
                                    cartIsEmptyScreen(scan);
                                    // leave cart screen
                                    break exit;
                                }
                                break;
                            }
                            // update cart item
                            cartItem.setAmount(quantity);
                            cartService.updateCartItem(cartItem);

                            clearScreen();
                            System.out.println("Update successful");
                            System.out.print("\nPress enter to continue...");
                            scan.nextLine();
                            break;
                        }
                        break;
                    case "3":
                        // TODO: navigate to payment
                        break exit;
                    case "x":
                        break exit;
                    default:
                        break;
                }
            }
        }
    }

    /* --------------------- Helper Methods ------------------------- */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void cartIsEmptyScreen(Scanner scan) {
        // option menu if cart is not found
        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the cart " + session.getUsername() +
                        "!");
                System.out.println("\nYou currently have nothing in your cart...");
                System.out.println("\n[1] Continue shopping");
                System.out.println("[x] Go back");

                System.out.print("\nChoose an option: ");
                switch (scan.nextLine()) {
                    case "1":
                        // navigate to product screen
                        router.navigate("/home", scan); // TODO: replace
                        break exit;
                    case "x":
                        break exit;
                    default:
                        break;
                }
            }
        }
    }

    private void showCartItem(CartItem cartItem) {
        System.out.println("\nProduct: " + cartItem.getName());
        System.out.println("Price: " + cartItem.getPrice());
        System.out.println("Amount: " + cartItem.getAmount());
    }

    private void showCartItems(List<CartItem> cartItems) {
        // loop through cart items
        for (CartItem cartItem : cartItems) {
            showCartItem(cartItem);
        }
    }

    private String getItemOption(String title, List<CartItem> cartItems, Scanner scan) {
        String input = "";
        while (true) {
            clearScreen();
            System.out.println(title);

            // loop through cart items
            int choiceCounter = 1;
            for (CartItem cartItem : cartItems) {
                System.out.println("\n    Product: " + cartItem.getName());
                System.out.println("[" + choiceCounter++ + "] " + "Price: " + cartItem.getPrice());
                System.out.println("    Amount: " + cartItem.getAmount());
            }
            System.out.println("\n[x] Exit");

            System.out.print("\nChoose an option: ");

            input = scan.nextLine();
            if (input.equalsIgnoreCase("x")) {
                return "x";
            } else if (!isValidNumber(input) || Integer.parseInt(input) > cartItems.size() ||
                    Integer.parseInt(input) < 1) {
                continue;
            }

            return input;
        }
    }

    private String getQuantity(int amount, int stock, Scanner scan) {
        String input = "";
        while (true) {
            clearScreen();
            System.out.println("Changing quantity...");
            System.out.println("\n- Current stock   : " + stock);
            System.out.println("- Current quantity: " + amount);
            System.out.print("\nEnter new quantity (x to cancel): ");

            input = scan.nextLine();
            if (input.equalsIgnoreCase("x")) {
                return "x";
            }
            // validate
            if (!isValidNumber(input) || Integer.parseInt(input) > stock || Integer.parseInt(input) < 0) {
                clearScreen();
                System.out.println("Input is invalid: must be a number between 0 and " + stock);
                System.out.print("\nEnter to continue...");
                scan.nextLine();
                continue;
            }
            break;
        }
        return input;
    }

    private boolean isValidNumber(String possibleNum) {
        if (possibleNum.length() == 0 || !Pattern.matches("[0-9]+", possibleNum)) {
            return false;
        }
        return true;
    }
}

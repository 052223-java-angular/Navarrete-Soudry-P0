package com.Revature.app.screens;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.Revature.app.models.Cart;
import com.Revature.app.models.CartItem;
import com.Revature.app.models.Order;
import com.Revature.app.models.OrderItem;
import com.Revature.app.models.Product;
import com.Revature.app.models.Session;
import com.Revature.app.services.CartService;
import com.Revature.app.services.OrderService;
import com.Revature.app.services.ProductsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartScreen implements IScreen {
    private final CartService cartService;
    private final OrderService orderService;
    private final ProductsService productsService;
    private Session session;
    private static final Logger logger = LogManager.getLogger(CartScreen.class);


    @Override
    public void start(Scanner scan) {
        logger.info("You have reached the Cart screen");
        Cart cart = cartService.findCartByCartId(session.getCart_id());
        List<CartItem> cartItems = cartService.findAllCartItemsByCartId(cart.getId());
        String itemOption = "";
        String itemQuantity = "";
        exit: {
            // empty cart screen
            if (cartItems.isEmpty()) {
                cartIsEmptyScreen(scan);
                break exit;
            }

            // cart screen
            while (true) {
                clearScreen();
                System.out.println("Welcome to the cart " + session.getUsername() +
                        "!");

                // show cart items
                showCartItems(cartItems);
                // options
                System.out.println("\n[1] Remove item");
                System.out.println("[2] Change item quantity");
                System.out.println("[3] Proceed to checkout");
                System.out.println("[x] Go back");

                // get option
                System.out.print("\nChoose an option: ");
                switch (scan.nextLine()) {
                    // remove item
                    case "1":
                        while (true) {
                            // get cart item option
                            itemOption = getCartItemOption(cartItems, scan);
                            if (itemOption.equals("x")) {
                                break;
                            }

                            CartItem cartItem = cartItems.get(Integer.parseInt(itemOption) - 1);

                            // update cart
                            cart.setTotal_cost(cart.getTotal_cost().subtract(cartItem.getPrice()));
                            cartService.updateCart(cart);

                            // delete cart item
                            cartService.deleteCartItem(cartItem.getId());
                            cartItems.remove(cartItem);

                            // empty cart screen
                            if (cartItems.isEmpty()) {
                                cartIsEmptyScreen(scan);
                                // leave cart screen
                                break exit;
                            }

                            // successful removal
                            clearScreen();

                            logger.info("User removed cart item");
                            System.out.println("Removal successful");
                            System.out.print("\nPress enter to continue...");
                            scan.nextLine();
                            break;
                        }
                        break;
                    // change quantity
                    case "2":
                        while (true) {
                            // get cart item option
                            itemOption = getCartItemOption(cartItems, scan);
                            if (itemOption.equals("x")) {
                                break;
                            }

                            CartItem cartItem = cartItems.get(Integer.parseInt(itemOption) - 1);

                            // get new quantity
                            itemQuantity = getQuantity(cartItem.getQuantity(), cartItem.getStock(), scan);
                            if (itemQuantity.equals("x")) {
                                continue;
                            }

                            if (Integer.parseInt(itemQuantity) == 0) {
                                // update cart
                                cart.setTotal_cost(cart.getTotal_cost().subtract(cartItem.getPrice()));
                                cartService.updateCart(cart);

                                // delete cart item
                                cartService.deleteCartItem(cartItem.getId());
                                cartItems.remove(cartItem);

                                // success removal
                                clearScreen();
                                System.out.println("Removal successful");
                                System.out.print("\nPress enter to continue...");
                                scan.nextLine();

                                // show empty cart screen
                                if (cartItems.isEmpty()) {
                                    cartIsEmptyScreen(scan);
                                    // leave cart screen
                                    break exit;
                                }

                                break;
                            }

                            // update cart and cart item
                            updateCartAndCartItem(cart, cartItem, Integer.parseInt(itemQuantity));

                            clearScreen();
                            System.out.println("Update successful");
                            System.out.print("\nPress enter to continue...");
                            scan.nextLine();
                            logger.info("User updated cart item.");
                            break;
                        }
                        break;
                    // checkout
                    case "3":
                        while (true) {
                            clearScreen();
                            System.out.println("Checking out...");
                            showCartItems(cartItems);
                            System.out.println("\n------------------------------------------------------------------");
                            System.out.println("\nTotal price: " + cart.getTotal_cost());
                            System.out.print("\nContinue with purchase (y/n): ");

                            switch (scan.nextLine()) {
                                case "y":
                                    // create order
                                    Order order = new Order(OffsetDateTime.now(), cart.getTotal_cost(),
                                            session.getId());
                                    orderService.createOrder(order);

                                    for (CartItem cartItem : cartItems) {
                                        // create order item
                                        OrderItem orderItem = new OrderItem(cartItem.getName(), cartItem.getQuantity(),
                                                cartItem.getPrice(),
                                                order.getId(), cartItem.getProduct_id());
                                        orderService.createOrderItem(orderItem);

                                        // delete cart item
                                        cartService.deleteCartItem(cartItem.getId());

                                        // update product stock
                                        Optional<Product> product = productsService
                                                .getProductById(orderItem.getProduct_id());
                                        product.get().setStock(product.get().getStock() - orderItem.getQuantity());
                                        productsService.updateProduct(product.get());
                                    }

                                    // update cart
                                    cart.setTotal_cost(BigDecimal.valueOf(0));
                                    cartService.updateCart(cart);

                                    // process payment
                                    clearScreen();
                                    System.out.println("Your order was processed successfully!");
                                    System.out.print("\nEnter to continue...");
                                    scan.nextLine();
                                    logger.info("User has checked out and processed the order successfully");
                                    break exit;
                                case "n":
                                    break;
                                default:
                                    continue;
                            }
                            break;
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
                System.out.println("\n[x] Go back");

                System.out.print("\nChoose an option: ");
                switch (scan.nextLine()) {
                    case "x":
                        break exit;
                    default:
                        break;
                }
            }
        }
    }

    private void showCartItems(List<CartItem> cartItems) {
        // loop through cart items
        for (CartItem cartItem : cartItems) {
            System.out.println("\n" +
                    cartItem.getName() + " - Price: $" + cartItem.getPrice() + " Quantity: " + cartItem.getQuantity());
        }
    }

    private void showCartItemOptions(List<CartItem> cartItems) {
        int counter = 1;
        for (CartItem cartItem : cartItems) {
            System.out.println("\n[" + counter + "] " + cartItem.getName() + " - Price: $" + cartItem.getPrice()
                    + " Quantity: " + cartItem.getQuantity());
            counter += 1;
        }
    }

    private String getCartItemOption(List<CartItem> cartItems, Scanner scan) {
        String input = "";
        while (true) {
            clearScreen();
            System.out.println("Choosing cart item...");

            // show cart item options
            showCartItemOptions(cartItems);

            System.out.print("\nChoose an option (x to cancel): ");

            input = scan.nextLine();
            if (input.equalsIgnoreCase("x")) {
                return "x";
            } else if (!isValidNumber(input) || Integer.parseInt(input) > cartItems.size() ||
                    Integer.parseInt(input) < 1) {
                clearScreen();
                System.out.println("Input is invalid: must be a number between 1 and " + cartItems.size());
                System.out.print("\nEnter to continue...");
                scan.nextLine();
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

    private void updateCartAndCartItem(Cart cart, CartItem cartItem, int quantity) {
        BigDecimal newPrice = cartItem.getPrice()
                .divide(BigDecimal.valueOf(cartItem.getQuantity()))
                .multiply(BigDecimal.valueOf(quantity));

        // update cart
        cart.setTotal_cost(cart.getTotal_cost().add(newPrice.subtract(cartItem.getPrice())));
        cartService.updateCart(cart);

        // update cart item
        cartItem.setPrice(newPrice);
        cartItem.setQuantity(quantity);
        cartService.updateCartItem(cartItem);
    }

    private boolean isValidNumber(String possibleNum) {
        if (possibleNum.length() == 0 || !Pattern.matches("[0-9]+", possibleNum)) {
            return false;
        }
        return true;
    }
}

package com.Revature.app.screens;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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

        // option menu if cart is not found;
        if (cartFound.isEmpty()) {
            exit: {
                while (true) {
                    clearScreen();
                    System.out.println("Welcome to the cart " + session.getUsername() +
                            "!");
                    System.out.println("\nYou currently have nothing in your cart...");
                    System.out.println("\n[1] Continue shopping");
                    System.out.println("[x] Exit");

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
            return;
        }

        // get cart items
        List<CartItem> cartItems = cartService.findAllCartItemsByCartId(cartFound.get().getId());
        // option menu for cart
        exit: {
            while (true) {
                clearScreen();
                // intro
                System.out.println("Welcome to the cart " + session.getUsername() +
                        "!");
                // display products in cart
                showCartProducts(cartItems);
                // options
                System.out.println("\n[1] Edit cart");
                System.out.println("[2] Proceed to payment");
                System.out.println("[x] Exit");

                System.out.print("\nChoose an option: ");
                switch (scan.nextLine()) {
                    case "1":
                        // edit cart
                        clearScreen();
                        break;
                    case "2":
                        // navigate to payment screen
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

    private void showCartProducts(List<CartItem> cartItems) {
        // loop through cart items
        for (CartItem cartItem : cartItems) {
            System.out.println("\nProduct: " + cartItem.getName());
            System.out.println("Price: " + cartItem.getPrice());
            System.out.println("Amount: " + cartItem.getAmount());
        }
    }
}

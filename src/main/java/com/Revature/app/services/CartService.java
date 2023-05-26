package com.Revature.app.services;

import java.util.List;
import java.util.Optional;

import com.Revature.app.daos.CartDAO;
import com.Revature.app.models.Cart;
import com.Revature.app.models.CartItem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartService {
    private final CartDAO cartDao;

    public Optional<Cart> findCartByUserId(String userId) {
        Optional<Cart> cartFound = cartDao.findCartByUserId(userId);
        if (cartFound.isEmpty()) {
            return Optional.empty();
        }
        return cartFound;
    }

    public List<CartItem> findAllCartItemsByCartId(String cartId) {
        List<CartItem> cartItems = cartDao.findAllCartItemsByCartId(cartId);
        return cartItems;
    }

    public void deleteCart(String cartId) {
        cartDao.deleteCart(cartId);
    }

    public void deleteCartItem(String cartItemId) {
        cartDao.deleteCartItem(cartItemId);
    }

    public void updateCartItem(CartItem cartItem) {

    }
}

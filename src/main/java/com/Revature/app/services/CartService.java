package com.Revature.app.services;

import java.util.List;

import com.Revature.app.daos.CartDAO;
import com.Revature.app.models.Cart;
import com.Revature.app.models.CartItem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartService {
    private final CartDAO cartDao;

    public void createCart(Cart cart) {
        cartDao.createCart(cart);
    }

    public void createCartItem(CartItem cartItem) {
        cartDao.createCartItem(cartItem);
    }

    public Cart findCartByUserId(String userId) {
        Cart cartFound = cartDao.findCartByUserId(userId);
        return cartFound;
    }

    public Cart findCartByCartId(String cartId) {
        return cartDao.findCartByCartId(cartId);
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

    public void updateCart(Cart cart) {
        cartDao.updateCart(cart);
    }

    public void updateCartItem(CartItem cartItem) {
        cartDao.updateCartItem(cartItem);
    }
}

package com.Revature.app.Services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Revature.app.daos.CartDAO;
import com.Revature.app.models.Cart;
import com.Revature.app.models.CartItem;

import com.Revature.app.services.CartService;

public class CartServiceTest {

    @Mock
    private CartDAO cartDao = new CartDAO();

    private CartService cartService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cartService = new CartService(cartDao);
    }

    @Test
    public void testCreateCart() {
        Cart cart = new Cart(BigDecimal.valueOf(0), "1");
        doNothing().when(cartDao).createCart(any(Cart.class));

        cartService.createCart(cart);
        verify(cartDao, times(1)).createCart(any(Cart.class));
    }

    @Test
    public void testCreateCartItem() {
        CartItem cartItem = new CartItem("Laptop", 50, 2, BigDecimal.valueOf(499.99), "1", "1");
        doNothing().when(cartDao).createCartItem(any(CartItem.class));

        cartService.createCartItem(cartItem);
        verify(cartDao, times(1)).createCartItem(any(CartItem.class));
    }

    @Test
    public void testDeleteCart() {
        Cart cart = new Cart(BigDecimal.valueOf(0), "1");
        doNothing().when(cartDao).deleteCart(cart.getId());

        cartService.deleteCart(cart.getId());
        verify(cartDao, times(1)).deleteCart(cart.getId());
    }

    @Test
    public void testDeleteCartItem() {
        CartItem cartItem = new CartItem("Laptop", 50, 2, BigDecimal.valueOf(499.99), "1", "1");
        doNothing().when(cartDao).deleteCartItem(cartItem.getId());

        cartService.deleteCartItem(cartItem.getId());
        verify(cartDao, times(1)).deleteCartItem(cartItem.getId());
    }

    @Test
    public void testUpdateCart() {
        Cart cart = new Cart(BigDecimal.valueOf(0), "1");
        doNothing().when(cartDao).updateCart(any(Cart.class));

        cartService.updateCart(cart);
        verify(cartDao, times(1)).updateCart(any(Cart.class));
    }

    @Test
    public void testUpdateCartItem() {
        CartItem cartItem = new CartItem("Laptop", 50, 2, BigDecimal.valueOf(499.99), "1", "1");
        doNothing().when(cartDao).updateCartItem(any(CartItem.class));

        cartService.updateCartItem(cartItem);
        verify(cartDao, times(1)).updateCartItem(any(CartItem.class));
    }

    @Test
    public void testFindCartByCartId() {
        Cart cart = new Cart(BigDecimal.valueOf(0), "1");
        when(cartDao.findCartByCartId(cart.getId())).thenReturn(cart);

        Cart foundCart = cartService.findCartByCartId(cart.getId());
        verify(cartDao, times(1)).findCartByCartId(cart.getId());
        assertEquals(cart, foundCart);
    }

    @Test
    public void testFindCartByUserId() {
        Cart cart = new Cart(BigDecimal.valueOf(0), "1");
        when(cartDao.findCartByCartId(cart.getUser_id())).thenReturn(cart);

        Cart foundCart = cartService.findCartByCartId(cart.getUser_id());
        verify(cartDao, times(1)).findCartByCartId(cart.getUser_id());
        assertEquals(cart, foundCart);
    }

    @Test
    public void testFindAllCartItemsByCartId() {
        Cart cart = new Cart(BigDecimal.valueOf(0), "1");
        List<CartItem> cartItems = new ArrayList<>();
        when(cartDao.findAllCartItemsByCartId(cart.getId())).thenReturn(cartItems);

        List<CartItem> actual = cartService.findAllCartItemsByCartId(cart.getId());
        verify(cartDao, times(1)).findAllCartItemsByCartId(cart.getId());
        assertEquals(cartItems, actual);
    }
}

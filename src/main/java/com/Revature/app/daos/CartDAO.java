package com.Revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.Revature.app.models.Cart;
import com.Revature.app.models.CartItem;
import com.Revature.app.utils.ConnectionFactory;

public class CartDAO {
    public void createCart(Cart cart) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO carts (id, total_cost, user_id) VALUES (?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, cart.getId());
                ps.setBigDecimal(2, cart.getTotal_cost());
                ps.setString(3, cart.getUser_id());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
    }

    public void createCartItem(CartItem cartItem) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO cart_items (id, quantity, price, cart_id, product_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, cartItem.getId());
                ps.setInt(2, cartItem.getQuantity());
                ps.setBigDecimal(3, cartItem.getPrice());
                ps.setString(4, cartItem.getCart_id());
                ps.setString(5, cartItem.getProduct_id());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
    }

    public Optional<Cart> findCartByUserId(String userId) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM carts WHERE user_id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, userId);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Cart cart = new Cart();
                        cart.setId(rs.getString("id"));
                        cart.setTotal_cost(rs.getBigDecimal("total_cost"));
                        cart.setUser_id(rs.getString("user_id"));
                        return Optional.of(cart);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }

        return Optional.empty();
    }

    public List<CartItem> findAllCartItemsByCartId(String cartId) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT cart_items.id, cart_items.quantity, cart_items.cart_id, cart_items.price, " +
                    "cart_items.product_id, products.stock, products.name FROM cart_items " +
                    "INNER JOIN products ON cart_items.product_id = products.id AND cart_items.cart_id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, cartId);

                try (ResultSet rs = ps.executeQuery()) {
                    List<CartItem> cartItems = new ArrayList<>();
                    while (rs.next()) {
                        CartItem cartItem = new CartItem();
                        cartItem.setId(rs.getString("id"));
                        cartItem.setStock(rs.getInt("stock"));
                        cartItem.setQuantity(rs.getInt("quantity"));
                        cartItem.setName(rs.getString("name"));
                        cartItem.setPrice(rs.getBigDecimal("price"));
                        cartItem.setCart_id(rs.getString("cart_id"));
                        cartItem.setProduct_id(rs.getString("product_id"));
                        cartItems.add(cartItem);
                    }
                    return cartItems;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
    }

    public void deleteCart(String cartId) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "DELETE FROM carts WHERE id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, cartId);

                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
    }

    public void deleteCartItem(String cartItemId) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "DELETE FROM cart_items WHERE id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, cartItemId);

                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
    }

    public void updateCart(Cart cart) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "UPDATE carts SET total_cost = ?, user_id = ? WHERE id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setBigDecimal(1, cart.getTotal_cost());
                ps.setString(2, cart.getUser_id());
                ps.setString(3, cart.getId());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
    }

    public void updateCartItem(CartItem cartItem) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "UPDATE cart_items SET quantity = ?, price = ?, cart_id = ?, product_id = ? WHERE id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, cartItem.getQuantity());
                ps.setBigDecimal(2, cartItem.getPrice());
                ps.setString(3, cartItem.getCart_id());
                ps.setString(4, cartItem.getProduct_id());
                ps.setString(5, cartItem.getId());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db");
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
    }
}

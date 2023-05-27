package com.Revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.Revature.app.models.Order;
import com.Revature.app.models.OrderItem;
import com.Revature.app.utils.ConnectionFactory;

public class OrderDAO {
    public void createOrder(Order order) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO orders (id, created_at, total_cost, user_id) VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, order.getId());
                ps.setObject(2, order.getCreated_at());
                ps.setBigDecimal(3, order.getTotal_cost());
                ps.setString(4, order.getUser_id());
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

    public void createOrderItem(OrderItem orderItem) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO order_items (id, quantity, price, order_id, product_id) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, orderItem.getId());
                ps.setInt(2, orderItem.getQuantity());
                ps.setBigDecimal(3, orderItem.getPrice());
                ps.setString(4, orderItem.getOrder_id());
                ps.setString(5, orderItem.getProduct_id());
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

    public List<Order> getAllOrdersByUserId(String userId) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM orders WHERE user_id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, userId);

                try (ResultSet rs = ps.executeQuery()) {
                    List<Order> orders = new ArrayList<>();
                    while (rs.next()) {
                        Order order = new Order();
                        order.setId(rs.getString("id"));
                        order.setCreated_at(rs.getObject("created_at", OffsetDateTime.class));
                        order.setTotal_cost(rs.getBigDecimal("total_cost"));
                        order.setUser_id(rs.getString("user_id"));
                        orders.add(order);
                    }
                    return orders;
                }
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

    public List<OrderItem> getAllOrderItemsByOrderId(String orderId) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT order_items.id, order_items.quantity, order_items.price, order_items.order_id, " +
                    "order_items.product_id, products.name FROM order_items INNER JOIN products ON " +
                    "order_items.product_id = products.id AND order_id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, orderId);

                try (ResultSet rs = ps.executeQuery()) {
                    List<OrderItem> orderItems = new ArrayList<>();
                    while (rs.next()) {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setId(rs.getString("id"));
                        orderItem.setName(rs.getString("name"));
                        orderItem.setQuantity(rs.getInt("quantity"));
                        orderItem.setPrice(rs.getBigDecimal("price"));
                        orderItem.setOrder_id(rs.getString("order_id"));
                        orderItem.setProduct_id(rs.getString("product_id"));
                        orderItems.add(orderItem);
                    }
                    return orderItems;
                }
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
}

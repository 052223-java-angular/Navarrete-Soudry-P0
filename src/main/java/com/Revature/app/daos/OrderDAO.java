package com.Revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}

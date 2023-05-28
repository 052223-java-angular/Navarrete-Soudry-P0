package com.Revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import com.Revature.app.models.Product;
import com.Revature.app.utils.ConnectionFactory;

public class ProductDAO {

    public Optional<List<Product>> grabAllAvailableProductsOptional() {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM products WHERE stock > 0";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                List<Product> p1 = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getBigDecimal("price"));
                        product.setStock(rs.getInt("stock"));
                        product.setCategory_id(rs.getString("category_id"));
                        p1.add(product);
                    }
                    return Optional.of(p1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
    }

    public Optional<List<Product>> grabAllProductBy(Float low, Float High) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM products WHERE price >= ? AND price <= ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setFloat(1, low);
                ps.setFloat(2, High);
                List<Product> p1 = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getBigDecimal("price"));
                        product.setStock(rs.getInt("stock"));
                        product.setCategory_id(rs.getString("category_id"));
                        p1.add(product);
                    }
                    return Optional.of(p1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
    }

    public Optional<List<Product>> searchByCategory(String qualifier) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT products.*, categories.name FROM products, categories WHERE products.category_id = categories.id AND categories.name = ?;";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, qualifier);

                List<Product> p1 = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getBigDecimal("price"));
                        product.setStock(rs.getInt("stock"));
                        product.setCategory_id(rs.getString("category_id"));
                        p1.add(product);
                    }
                    return Optional.of(p1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }

    }

    public Optional<List<Product>> searchByName(String qualifier) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM products WHERE name = ? OR name LIKE ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, qualifier);
                ps.setString(2, "%" + qualifier + "%");

                List<Product> p1 = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {

                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getBigDecimal("price"));
                        product.setStock(rs.getInt("stock"));
                        product.setCategory_id(rs.getString("category_id"));
                        p1.add(product);
                    }
                    return Optional.of(p1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc");
        }
    }
}

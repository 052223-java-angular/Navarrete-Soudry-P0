package com.Revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import com.Revature.app.models.Review;

// import com.Revature.app.models.User;
import com.Revature.app.utils.ConnectionFactory;


public class ReviewDAO {

    public Optional<List<Review>> grabReviewByName(String itemName) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
        String sql = "SELECT * FROM reviews, products WHERE reviews.product_id = products.id AND products.name = ?";;
    
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, itemName);
              
                List<Review> reviews = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Review review = new Review();
                        review.setRating(rs.getInt("rating"));
                        review.setDescription(rs.getString("description"));
                        reviews.add(review);
                    }
                    return Optional.of(reviews);
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

    public void insertReview(Review review) {
    
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO reviews (id, rating, description, product_id, user_id) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, review.getId());
                ps.setInt(2, review.getRating());
                ps.setString(3, review.getDescription());
                ps.setString(4, review.getProduct_id());
                ps.setString(5, review.getUser_id());
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
    


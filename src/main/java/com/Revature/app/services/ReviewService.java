package com.Revature.app.services;
import com.Revature.app.models.Review;
import com.Revature.app.daos.ReviewDAO;
import java.util.Optional;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReviewService {
    
    private final ReviewDAO revDao;
    public void sendAReview(Review review) {
        revDao.insertReview(review);
    }
    public Optional<List<Review>> getReview(String reviewedItem) {
        Optional<List<Review>> reviews = revDao.grabReviewByName(reviewedItem);
        if (reviews.isPresent()) {
            return Optional.of(reviews.get());
        } else {
            return Optional.empty();
        }
    }   
}
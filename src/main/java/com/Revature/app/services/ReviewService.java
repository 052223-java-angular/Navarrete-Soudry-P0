package com.Revature.app.services;
import com.Revature.app.models.Review;
import com.Revature.app.daos.ReviewDAO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReviewService {
    private final ReviewDAO revDao;
        public void sendAReview(Review review) {
        revDao.insertReview(review);
    }   
}
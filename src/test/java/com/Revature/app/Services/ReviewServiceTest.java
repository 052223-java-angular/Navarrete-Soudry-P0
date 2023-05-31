package com.Revature.app.Services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import org.junit.runner.RunWith;

import com.Revature.app.daos.ReviewDAO;
import com.Revature.app.models.Review;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.Revature.app.services.ReviewService;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceTest {

    @Mock
    private ReviewDAO reviewDAO;
    // @Mock
    private ReviewService reviewService;

    @Before
    public void setUp() {
        // Initialize the Mockito framework
        MockitoAnnotations.openMocks(this);

        // Create a new instance of the UserService class with the mocked dependencies
        reviewService = new ReviewService(reviewDAO);
    }

    @Test
    public void sendAReviewTest() {
        // Initial test values
        Integer rating = 5;
        String Description = "Really cool description";
        String product_id = "2ea3b385-ec1c-423a-a530-5550a399f8c5";
        String user_id = "2e45b385-ec1c-423a-a530-5550a399f8c5";
        Review samplReview = new Review(rating, Description, product_id, user_id);

        reviewService.sendAReview(samplReview);
        verify(reviewDAO, times(1)).insertReview(samplReview);
    }

    @Test
    public void getAReviewTest() {
        String id = "2ea3b385-ec1c-423a-a530-5550a397f8c5";
        String id2 = "3ea3b385-ec1c-423a-a530-5550a397f8c5";
        Integer rating = 5;
        String Description = "Really cool description";
        String product_id = "2ea3b385-ec1c-423a-a530-5550a399f8c5";
        String user_id = "2e45b385-ec1c-423a-a530-5550a399f8c5";
        String reviewedItem = "Labtop";

        // Create a list of reviews to be returned by the mock
        List<Review> mockReviews = new ArrayList<>();
        mockReviews.add(new Review(id, rating, Description, product_id, user_id));
        mockReviews.add(new Review(id2, rating, Description, product_id, user_id));

        // Mock the behavior of the revDao.grabReviewByName(reviewedItem) method
        when(reviewDAO.grabReviewByName(reviewedItem)).thenReturn(Optional.of(mockReviews));

        // Invoke the method under test
        Optional<List<Review>> result = reviewService.getReview(reviewedItem);

        // Assert the result
        assertTrue(result.isPresent());
        assertEquals(mockReviews, result.get());

        // Verify the interaction with the reviewDAO
        verify(reviewDAO).grabReviewByName(reviewedItem);
        verify(reviewDAO, times(1)).grabReviewByName(reviewedItem);
    }
}
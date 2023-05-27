package com.Revature.app.services;
import com.Revature.app.daos.ProductDAO;
import  com.Revature.app.models.Product;
import com.Revature.app.models.Review;
import com.Revature.app.daos.ReviewDAO;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductsService {
    private final ProductDAO dao;
    private final ReviewDAO revDao;

    public Optional<List<Product>> getAll() {
    Optional<List<Product>> p1 = dao.grabAllAvailableProductsOptional();
    if (p1.isPresent()) {
        List<Product> productList = p1.get();
        printResult(productList);
        return Optional.of(new ArrayList<>(productList));
        }
    else {
        // Handle the case when the Optional does not contain a value
        System.out.println("There are no products available at this time.");
        return Optional.empty();
    }
    }

    public Optional<List<Product>> getByName(String value) {
        System.out.print("test123");
        Optional<List<Product>> p1 = dao.searchByName(value);
        if (p1.isPresent()) {
            List<Product> productList = p1.get();
            printResult(productList);
              return Optional.of(new ArrayList<>(productList));
        }  else {
            // Handle the case when the Optional does not contain a value
            System.out.println("There are no products in this category.");
            return Optional.empty();
        }
    }
       
    public Optional<List<Product>> getByCategory(String value) {
        System.out.print("test123");
        Optional<List<Product>> p1 = dao.searchByCategory(value);
        if (p1.isPresent()) {
            List<Product> productList = p1.get();
            printResult(productList);
            return Optional.of(new ArrayList<>(productList));
            }
        else {
            // Handle the case when the Optional does not contain a value
            System.out.println("There are no products in this category.");
            return Optional.empty();
        }
        }

    public Optional<List<Product>> getByPrice(Float f1, Float f2) {
    Optional<List<Product>> p1 = dao.grabAllProductBy(f1, f2);
    if (p1.isPresent()) {
        List<Product> productList = p1.get();
        printResult(productList);
        return Optional.of(new ArrayList<>(productList));
        }
    else {
        // Handle the case when the Optional does not contain a value
        System.out.println("There are no products in this price Range.");
        return Optional.empty();
    }
    }

    public Optional<List<Review>> getReview(String reviewedItem) {
        Optional<List<Review>> reviews = revDao.grabReviewByName(reviewedItem);
        if (reviews.isPresent()) {
            return Optional.of(reviews.get());
        } else {
            return Optional.empty();
        }
       
    }

    // public Optional<List<Review>> sendAReview(Review review) {
    //     System.out.println("What out of 5 stars would you give this number?");
    //     revDao.insertReview(review));
    // }


       /*
     * ------------------------ Helper methods ------------------------------
     */

    private void printResult(List<Product> P) {
        clearScreen();
        System.out.println("YOUR PRODUCTS!!!!!");
        System.out.print("Name ");
        // System.out.print("Description ");
        System.out.print("Price ");
        System.out.print("Stock ");
        System.out.println("Category ");
        for (Product product : P) {
            // Perform operations on each product
            System.out.print(product.getName() + " " );
            // System.out.print(product.getDescription() + " " );
            System.out.print(product.getPrice() + " " );
            System.out.print(product.getStock() + " " );
            System.out.println(product.getCategory_id() + " " );
            // System.out.println("-------------------------");
    }
 }

private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}
}
package com.Revature.app.services;
import com.Revature.app.daos.ProductDAO;
import  com.Revature.app.models.Product;
import java.util.Optional;
import java.util.List;
// import java.util.ArrayList;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductsService {
    private final ProductDAO dao;

    public void getAll() {
    Optional<List<Product>> p1 = dao.grabAllAvailableProductsOptional();
    if (p1.isPresent()) {
        List<Product> productList = p1.get();
        printResult(productList);
        }
    else {
        // Handle the case when the Optional does not contain a value
        System.out.println("There are no products available at this time.");
    }
    }

    public void getByName(String value) {
        System.out.print("test123");
        Optional<List<Product>> p1 = dao.searchByName(value);
        if (p1.isPresent()) {
            List<Product> productList = p1.get();
            printResult(productList);
            }
        else {
            // Handle the case when the Optional does not contain a value
            System.out.println("There are no products in this category.");
        }
    }

    public void getByCategory(String value) {
        System.out.print("test123");
        Optional<List<Product>> p1 = dao.searchByCategory(value);
        if (p1.isPresent()) {
            List<Product> productList = p1.get();
            printResult(productList);
            }
        else {
            // Handle the case when the Optional does not contain a value
            System.out.println("There are no products in this category.");
        }
        }

    public void getByPrice(Float f1, Float f2) {
    Optional<List<Product>> p1 = dao.grabAllProductBy(f1, f2);
    if (p1.isPresent()) {
        List<Product> productList = p1.get();
        printResult(productList);
        }
    else {
        // Handle the case when the Optional does not contain a value
        System.out.println("There are no products in this price Range.");
    }
    }

    private void printResult(List<Product> P) {
        clearScreen();
        for (Product product : P) {
            // Perform operations on each product
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Stock: " + product.getStock());
            System.out.println("Category ID: " + product.getCategory_id());
            System.out.println("-------------------------");
    }
 }

private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}}
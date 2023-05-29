package com.Revature.app.services;

import com.Revature.app.daos.ProductDAO;
import com.Revature.app.models.Product;


import java.util.Optional;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductsService {
    private final ProductDAO dao;

    public Optional<List<Product>> getAll() {
    Optional<List<Product>> p1 = dao.grabAllAvailableProductsOptional();
    if (p1.isPresent()) {
        List<Product> productList = p1.get();
        return Optional.of(new ArrayList<>(productList));
    } else {
         // Handle the case when the Optional does not contain a value
            System.out.println("There are no products available at this time.");
            return Optional.empty();
    }
   }   
    
    public Optional<Product> getProductById(String productId) {
        Optional<Product> product = dao.getProductById(productId);
        if (product.isEmpty()) {
            return Optional.empty();
        }
        return product;
    }

    public Optional<List<Product>> getByName(String value) {
        System.out.print("test123");
        Optional<List<Product>> p1 = dao.searchByName(value);
        if (p1.isPresent()) {
            List<Product> productList = p1.get();

              return Optional.of(new ArrayList<>(productList));
        }  else {
            // printResult(productList);
            return Optional.empty();
            // return Optional.of(new ArrayList<>(productList));
        }
    }

    public Optional<List<Product>> getByCategory(String value) {
        System.out.print("test123");
        Optional<List<Product>> p1 = dao.searchByCategory(value);
        if (p1.isPresent()) {
            List<Product> productList = p1.get();
            return Optional.of(new ArrayList<>(productList));
        } else {
            // Handle the case when the Optional does not contain a value
            System.out.println("There are no products in this category.");
            return Optional.empty();
        }
    }

    public Optional<List<Product>> getByPrice(BigDecimal f1, BigDecimal f2) {
        Optional<List<Product>> p1 = dao.grabAllProductBy(f1, f2);
        if (p1.isPresent()) {
            List<Product> productList = p1.get();
            return Optional.of(new ArrayList<>(productList));
        } else {
            //  Handle the case when the Optional does not contain a value
            System.out.println("There are no products in this price Range.");
            return Optional.empty();
        }
    }

    public void updateProduct(Product product) {
        dao.updateProduct(product);
    }
}
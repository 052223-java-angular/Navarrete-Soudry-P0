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
        System.out.println("Made it to Dao");
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM products WHERE stock > 0";
            // String sql = "SELECT * FROM products";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
              
                List<Product> p1 = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {

                        Product product = new Product();
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getFloat("price"));
                        product.setStock(rs.getInt("stock"));
                        product.setCategory_id(rs.getString("category_id"));
                        p1.add(product);
                      
                    }
                    // System.out.println("The number of items selected is " + p1.size());
                    // for (Product product : p1) {
                    //     System.out.println("ID: " + product.getId());
                    //     System.out.println("Name: " + product.getName());
                    //     System.out.println("Description: " + product.getDescription());
                    //     System.out.println("Price: " + product.getPrice());
                    //     System.out.println("Stock: " + product.getStock());
                    //     System.out.println("Category ID: " + product.getCategory_id());
                    //     System.out.println("-------------------------");
                    // }
                    
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


// public void grabAllProductBy(String qualifier) {
//     if (checkIfCategory(qualifier)) {
//         searchByCategory(qualifier);
//     } else {
//         searchByName(qualifier);
//     }
// }

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
                    product.setPrice(rs.getFloat("price"));
                    product.setStock(rs.getInt("stock"));
                    product.setCategory_id(rs.getString("category_id"));
                    p1.add(product);
                  
                }
                // for (Product product : p1) {
                //     System.out.println("ID: " + product.getId());
                //     System.out.println("Name: " + product.getName());
                //     System.out.println("Description: " + product.getDescription());
                //     System.out.println("Price: " + product.getPrice());
                //     System.out.println("Stock: " + product.getStock());
                //     System.out.println("Category ID: " + product.getCategory_id());
                //     System.out.println("-------------------------");
                // }
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

// private boolean checkIfCategory(String value) {
//     try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//         String sql = "SELECT * FROM categories WHERE name == ?";

//         try (PreparedStatement ps = conn.prepareStatement(sql)) {
//             ps.setString(1, value);
//             try (ResultSet rs = ps.executeQuery()) {
//                 if (rs.next()) {
//                     Optional<String> categoryNameOpt = Optional.of(rs.getString("name"));
//                     if (categoryNameOpt.isEmpty()) {
//                         return false;
//                     }
//                     return true;
//                 }
//             }
//         }
//     } catch (SQLException e) {
//         throw new RuntimeException(e);
//     } catch (IOException e) {
//         throw new RuntimeException("Cannot find application.properties");
//     } catch (ClassNotFoundException e) {
//         throw new RuntimeException("Unable to load JDBC driver");
//     }
//     return false;
// }

    public Optional<List<Product>> searchByCategory(String qualifier) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT products.*, categories.name FROM products, categories WHERE products.category_id = categories.id AND categories.name = ?;";
    
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, qualifier);
              
                List<Product> p1 = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
    
                        Product product = new Product();
    
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getFloat("price"));
                        product.setStock(rs.getInt("stock"));
                        product.setCategory_id(rs.getString("category_id"));
                        p1.add(product);
                      
                    }
                    // for (Product product : p1) {
                    //     System.out.println("ID: " + product.getId());
                    //     System.out.println("Name: " + product.getName());
                    //     System.out.println("Description: " + product.getDescription());
                    //     System.out.println("Price: " + product.getPrice());
                    //     System.out.println("Stock: " + product.getStock());
                    //     System.out.println("Category ID: " + product.getCategory_id());
                    //     System.out.println("-------------------------");
                    // }
                    
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
            String sql = "SELECT * FROM products WHERE name = ? OR name LIKE '%?%'";
    
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, qualifier);
              
                List<Product> p1 = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
    
                        Product product = new Product();
    
                        product.setId(rs.getString("id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getFloat("price"));
                        product.setStock(rs.getInt("stock"));
                        product.setCategory_id(rs.getString("category_id"));
                        p1.add(product);
                      
                    }
                    // for (Product product : p1) {
                    //     System.out.println("ID: " + product.getId());
                    //     System.out.println("Name: " + product.getName());
                    //     System.out.println("Description: " + product.getDescription());
                    //     System.out.println("Price: " + product.getPrice());
                    //     System.out.println("Stock: " + product.getStock());
                    //     System.out.println("Category ID: " + product.getCategory_id());
                    //     System.out.println("-------------------------");
                    // }
                    
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

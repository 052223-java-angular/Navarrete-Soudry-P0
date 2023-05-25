package com.Revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.Revature.app.models.User;

import com.Revature.app.utils.*;

public class RegistrationDAO {
    
  public Optional<User> findByUsername(String username) {
    return Optional.of( new User("test", "123"));
  }

  public void registerUser(User obj) {
      try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
       
          String sql = "INSERT INTO users (id, username, password, role_id) VALUES (?, ?, ?, ?)";

          try (PreparedStatement ps = conn.prepareStatement(sql)) {
              ps.setString(1, obj.getId());
              ps.setString(2, obj.getUsername());
              ps.setString(3, obj.getPassword());
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

  
  public Optional<String> searchByUserName(String username) {
      try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
        System.out.println("The conn " + conn);
          String sql = "SELECT * FROM users WHERE username = ?";

          try (PreparedStatement ps = conn.prepareStatement(sql)) {
              ps.setString(1, username);

              try (ResultSet rs = ps.executeQuery()) {
                  if (rs.next()) {
                    String value = rs.getString("username");
                    return Optional.of(value);
                  }
              }
          }

      } catch (SQLException e) {
          throw new RuntimeException( e
            // "Unable to connect to db"
            );
      } catch (IOException e) {
          throw new RuntimeException("Cannot find application.properties");
      } catch (ClassNotFoundException e) {
          throw new RuntimeException("Unable to load jdbc");
      }

      return Optional.empty();
  }
}
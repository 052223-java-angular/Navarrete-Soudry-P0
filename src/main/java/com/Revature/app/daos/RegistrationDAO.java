package com.Revature.app.daos;

import java.util.Optional;

import com.Revature.app.models.User;

public class RegistrationDAO {
    
  public Optional<User> findByUsername(String username) {
    return Optional.of( new User("test", "123"));
  }
}
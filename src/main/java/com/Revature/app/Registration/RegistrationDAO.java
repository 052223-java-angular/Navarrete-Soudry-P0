package com.Revature.app.Registration;

import java.util.Optional;

public class RegistrationDAO {
    
  public Optional<User> findByUsername(String username) {
    return Optional.of( new User("test", "123"));
  }
}
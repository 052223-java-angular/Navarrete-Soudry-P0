package com.Revature.app.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.Revature.app.daos.UserDAO;
import com.Revature.app.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserDAO userDao;

    public Optional<User> login(String username, String password) {
        Optional<User> userOpt = userDao.findByUsername(username);
        // check if no user was found
        // if user found also check if passwords don't match
        if (userOpt.isEmpty() || !BCrypt.checkpw(password, userOpt.get().getPassword())) {
            return Optional.empty();
        }
        return userOpt;
    }

    public User register(String username, String password) {
        return null;
    }

    public boolean isValidUsername(String username) {
        return username.matches("^\\w{5,10}$");
    }

    public boolean usernameAlreadyExists(String username) {
        Optional<String> userOpt = userDao.searchByUserName(username);

        if (userOpt.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean isValidPassword(String password) {
        return password.matches("^[a-zA-Z0-9]{5,10}$");
    }

    public void Register(String username, String password) {
        System.out.println("You reached the service");
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed);
        userDao.registerUser(newUser);
        System.out.println(newUser);
    }

}
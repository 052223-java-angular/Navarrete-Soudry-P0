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

    public boolean isValidUsername(String username) {
        // username must be 8 - 20 characters long
        // no _ or . at the beginning
        // no _ or _. or ._ or .. inside
        // no _ or . at the end
        // allowed characters: a-zA-Z0-9._
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isValidPassword(String password) {
        // ensures there is at least 1 number at least one of the listed symbols and can
        // only contain the following symbols !@#$%^&*_ and has to be within 4 to 10
        // characters
        return password.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*_]{4,10}$");
    }

    public boolean usernameAlreadyExists(String username) {
        Optional<User> userOpt = userDao.findByUsername(username);

        if (userOpt.isEmpty()) {
            return true;
        }
        return false;
    }

    // public String Hashing(String Password) {

    // }

}
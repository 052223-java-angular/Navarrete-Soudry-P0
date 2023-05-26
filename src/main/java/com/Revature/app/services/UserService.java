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
    //   It ensures the username needs to be 5-10 characters long
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
            //   It ensures the username needs to be 5-10 characters long
        return password.matches("^(?=.*\\d)[\\w!@#$%^&*]{5,10}$");

    }

    public void Register(String username, String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed);
        userDao.registerUser(newUser);
        System.out.println(newUser);
    }

}
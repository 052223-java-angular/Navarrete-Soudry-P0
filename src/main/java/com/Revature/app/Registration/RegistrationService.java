package com.Revature.app.Registration;

import java.util.Optional;


import org.mindrot.jbcrypt.BCrypt;


public class RegistrationService {
    
    public final RegistrationDAO dao;

    public RegistrationService(RegistrationDAO dao) {
        this.dao = dao;
    }

    public boolean isValidUsername(String username) {
    //   ensures there is at least 1 number at least one of the listed symbols and can only contain the following symbols !@#$%^&*_ and has to be within 4 to 10 characters
    return username.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*_]{4,10}$");
    }


    public boolean usernameAlreadyExists(String username) {
        //   ensures there is at least 1 number at least one of the listed symbols and can only contain the following symbols !@#$%^&*_ and has to be within 4 to 10 characters
        return username.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*_]{4,10}$");
        }

    public boolean isValidPassword(String username) {
        Optional<User> userOpt = dao.findByUsername(username);

        if (userOpt.isEmpty()) {
            return true;
        }
        return false;
    }

    // public String Hashing(String Password) {

    // }

}
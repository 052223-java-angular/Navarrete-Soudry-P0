package com.Revature.app.services;

import java.util.Scanner;

import com.Revature.app.screens.HomeScreen;
import com.Revature.app.screens.RegistrationScreen;
// import com.Revature.app.services.RegistrationService;

public class RouterService {


    RegistrationService registrationService = new RegistrationService();
    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                new HomeScreen().start(scan);
                break;
            case "/login":
                break;
            case "/register":
                new RegistrationScreen(registrationService).start(scan);
                break;
            default:
                break;
        }
    }
}

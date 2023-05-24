package com.Revature.app.services;

import java.util.Scanner;

import com.Revature.app.screens.HomeScreen;

public class RouterService {
    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                new HomeScreen().start(scan);
                break;
            case "/login":
                break;
            case "/register":
                break;
            default:
                break;
        }
    }
}

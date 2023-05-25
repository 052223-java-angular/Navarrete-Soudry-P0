package com.Revature.app;

import java.util.Scanner;

import com.Revature.app.models.Session;
import com.Revature.app.services.RouterService;

public class App {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        RouterService router = new RouterService(new Session());
        router.navigate("/home", scan);
        scan.close();
    }

}

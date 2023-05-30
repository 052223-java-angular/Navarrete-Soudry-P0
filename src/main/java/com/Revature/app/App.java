package com.Revature.app;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.Revature.app.models.Session;
import com.Revature.app.services.RouterService;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    public static void main(String args[]) {
        logger.info("-------------------Start Application----------------");
        Scanner scan = new Scanner(System.in);
        RouterService router = new RouterService(new Session());
        router.navigate("/home", scan);
        scan.close();
        logger.info("-------------------End Application----------------");
    }
}
package com.Revature.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.Revature.app.services.RouterService;
// import com.Revature.app.utils.ConnectionFactory;

public class App {
    public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException {
        // System.out.println(ConnectionFactory.getInstance().getConnection());
        Scanner scan = new Scanner(System.in);
        RouterService router = new RouterService();
        router.navigate("/home", scan);
        scan.close();
    }

}

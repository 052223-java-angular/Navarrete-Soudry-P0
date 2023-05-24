package com.Revature.app;

import com.Revature.app.Registration.*;

import java.util.Scanner;

public class App {

  // Cleans up Code
  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}
  public static void main(String args[]) {
    RegistrationScreen r1 = new RegistrationScreen(null);
    clearScreen();
    System.out.println("Welcome to eCommerce App!");
    Scanner scan = new Scanner(System.in);
    r1.testLogic(scan);
    scan.close();
  }
}

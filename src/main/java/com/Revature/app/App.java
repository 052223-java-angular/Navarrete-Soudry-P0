package com.Revature.app;

// import java.io.IOException;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;

// import com.Revature.app.utils.ConnectionFactory;

public class App {
    public static void main(String args[]) {
        System.out.println("Welcome to eCommerce App!");
      
      
        // Cleans up Code
  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}
      
      
      
        RegistrationScreen r1 = new RegistrationScreen(null);
    clearScreen();
    System.out.println("Welcome to eCommerce App!");
    Scanner scan = new Scanner(System.in);
    r1.testLogic(scan);
    scan.close();

        // /* -------- test --------- */
        // try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
        // System.out.println(conn.getSchema());
        // String sql = "INSERT INTO users (userid, username, password) VALUES (?, ?,
        // ?)";

        // try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
        // preparedStatement.setString(1, "1");
        // preparedStatement.setString(2, "tester1");
        // preparedStatement.setString(3, "password");
        // preparedStatement.executeUpdate();
        // }

        // } catch (SQLException e) {
        // System.out.println(e.getMessage());
        // } catch (IOException e) {
        // throw new RuntimeException("Cannot find application.properties");
        // } catch (ClassNotFoundException e) {
        // throw new RuntimeException("Unable to load jdbc");
        // }
    }

}

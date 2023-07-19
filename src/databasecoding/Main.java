package databasecoding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main  {

    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mariadb://192.168.1.7/daadb";
        String user = "daauser";
        String pass = "daapassword";

        // Sample data for the new person to be inserted
        String name = "Smith";
        String vorname = "John";
        double groesse = 1.80;
        double gewicht = 78.5;
        String kategorie = "Normalgewicht";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establish the database connection
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection to the database established.");

            // SQL query with placeholders (prepared statement)
            String sql = "INSERT INTO personen (Name, Vorname, Groesse, Gewicht, Kategorie) VALUES (?, ?, ?, ?, ?)";

            // Create a prepared statement to avoid SQL injection
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, vorname);
            preparedStatement.setDouble(3, groesse);
            preparedStatement.setDouble(4, gewicht);
            preparedStatement.setString(5, kategorie);

            // Execute the query to insert the data
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Row inserted successfully!");
            } else {
                System.out.println("Failed to insert row. No rows were affected.");
            }

            // Display all rows in the "personen" table after insertion
            displayPersonenTable(connection);

        } catch (SQLException e) {
            System.out.println("Error occurred while inserting the row: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the prepared statement and connection to release resources
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Helper method to display all rows in the "personen" table
    private static void displayPersonenTable(Connection connection) throws SQLException {
        String sql = "SELECT * FROM personen";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        System.out.println("\nCurrent rows in 'personen' table:");
        while (rs.next()) {
            int personID = rs.getInt("PersonID");
            String name = rs.getString("Name");
            String vorname = rs.getString("Vorname");
            double groesse = rs.getDouble("Groesse");
            double gewicht = rs.getDouble("Gewicht");
            String kategorie = rs.getString("Kategorie");

            System.out.println("PersonID: " + personID + ", Name: " + name + ", Vorname: " + vorname +
                    ", Größe: " + groesse + ", Gewicht: " + gewicht + ", Kategorie: " + kategorie);
        }
    }
}


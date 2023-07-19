package drivertest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Drivertest {

    public static void main(String[] args) {
    	
    	// driver for mysql database:
    	// https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-8.1.0.zip
    	//
        // String driver = "com.mysql.cj.jdbc.Driver";
        // String url = "jdbc:mysql://192.168.1.7/daadb";
    	
    	// driver for mariadb database:
    	// https://dlm.mariadb.com/2912798/Connectors/java/connector-java-3.1.4/mariadb-java-client-3.1.4.jar
    	//
        // String driver = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mariadb://192.168.1.7/daadb";
        
    	
        String user = "daauser";
        String pass = "daapassword";


        Connection connection = null;

        try {
            // Optional: Class.forName(driver); // Not strictly necessary for JDBC 4.0+
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Verbindung zur Datenbank hergestellt.");
        } catch (SQLException e) {
            System.out.println("Verbindung zur DB nicht möglich (" + e.getMessage() + ")");
            e.printStackTrace();
        } 
        
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM personen";
            ResultSet rs = statement.executeQuery(sql);

            // Get column names
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            System.out.println();

            // Print rows
            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        
        if (connection != null) {
           try {
        	   connection.close();
               System.out.println("Verbindung geschlossen.");
           } catch (SQLException e) {
        	   e.printStackTrace();
           }
        }
    }
}

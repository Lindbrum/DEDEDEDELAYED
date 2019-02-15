package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {

    public static Connection Connect() {

        // DB Config
        String dbAddress = "jdbc:mysql://localhost:3306/ambiental_monitoring";
        String dbUser = "root";
        String dbPassword = "";
        Connection dbConnection = null;

        // Check if JDBC driver exists
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return dbConnection;
        }

        // Connects to the DB
        try {
            dbConnection = DriverManager.getConnection(dbAddress, dbUser, dbPassword);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return dbConnection;
    }

}

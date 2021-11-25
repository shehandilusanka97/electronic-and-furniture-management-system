package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConnection {
    private static DBConnection dbConnection1;
    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myProject", "root", "root");
    }

    public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
        return (dbConnection1 == null) ? (new DBConnection()) : (dbConnection1);
    }

    public Connection getConnection() {
        return connection;
    }

}

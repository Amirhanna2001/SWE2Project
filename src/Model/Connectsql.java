package Model;
import java.sql.Connection;
import java.sql.*;


public class Connectsql {

    public static Connection setConnection() {
        Connection connectToServer = null;
        try {
            String url = "jdbc:mysql://localhost:3306/parking";
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "root";
            String password = "";
            connectToServer = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Not Connected");
        }
        return connectToServer;
    }
}

package Model;
import java.sql.Connection;
import java.sql.*;


public class Connectsql {

    private static  Connection connectToServer;
    public  Connectsql() {
        try {
            String url = "jdbc:mysql://localhost:3306/parking";
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "root";
            String password = "";
            connectToServer = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Not Connected");
        }
        //return connectToServer;
    }
    public static  Connection getConnection(){
        return connectToServer;
    }
}

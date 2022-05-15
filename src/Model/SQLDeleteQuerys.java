package Model;

import java.sql.*;
import static Model.Connectsql.setConnection;

public class SQLDeleteQuerys {
    
    public static void executeDeleteQuery(String tableName , String condition ){
        try {
            Connection connectToServer = setConnection();
            Statement statement = connectToServer.createStatement();
            statement.executeUpdate("DELETE FROM "+tableName+" WHERE "+condition);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
    public static void executeDeleteQueryLimitaion(String tableName){
        try {
            Connection connectToServer = setConnection();
            Statement statement = connectToServer.createStatement();
            statement.executeUpdate("DELETE FROM "+tableName+" limit 1 ");
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
    
}

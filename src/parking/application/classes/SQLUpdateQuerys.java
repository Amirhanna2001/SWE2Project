package parking.application.classes;

import java.sql.*;
import static parking.application.classes.Connectsql.setConnection;

public class SQLUpdateQuerys {

    public static void executeInsertQuery(String tableName, String valueName) {
        try {
            java.sql.Connection connectToServer = setConnection();
            Statement statement = connectToServer.createStatement();
            statement.executeUpdate("INSERT INTO " + tableName + " VALUES (" + valueName + ")");
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public static void executeUpdateQuerys(String tableAndColnomName, String newValue, long id) {
        try {
            Connection connectToServer = setConnection();
            Statement statement = connectToServer.createStatement();
            statement.executeUpdate("UPDATE " + tableAndColnomName + " = '" + newValue + "' WHERE ID = " + id + "");
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}

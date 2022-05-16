package Model;

import static Model.Connectsql.setConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLQueries {

    static Connection connectToServer;
    static Statement statement;
    
    
    public static void executeDeleteQuery(String tableName, String condition) {
        try {
            connectToServer = setConnection();
            statement = connectToServer.createStatement();
            statement.executeUpdate("DELETE FROM " + tableName + " WHERE " + condition);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public static void executeDeleteQueryLimitaion(String tableName) {
        try {
            connectToServer = setConnection();
            statement = connectToServer.createStatement();
            statement.executeUpdate("DELETE FROM " + tableName + " limit 1 ");
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public static ResultSet executeSelectQueryWithoutCondition(String columnName, String tableName) {
        ResultSet resultSetFromTable = null;
        try {
            connectToServer = setConnection();
            statement = connectToServer.createStatement();
            resultSetFromTable = statement.executeQuery("SELECT " + columnName + " FROM " + tableName);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return resultSetFromTable;
    }

    public static ResultSet executeSelectQueryWithCondition(String columnName, String tableName, String condition) {
        ResultSet resultSetFromTable = null;
        try {
            connectToServer = setConnection();
            statement = connectToServer.createStatement();
            resultSetFromTable = statement.executeQuery("SELECT " + columnName + " FROM " + tableName + " WHERE " + condition);
            resultSetFromTable.next();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return resultSetFromTable;
    }

    public static ResultSet executeSelectQueryLimitaion(String tableName) {
        ResultSet resultSetFromTable = null;
        try {
            connectToServer = setConnection();
            statement = connectToServer.createStatement();
            resultSetFromTable = statement.executeQuery("SELECT * FROM " + tableName + " limit 1");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return resultSetFromTable;
    }

    public static void executeInsertQuery(String tableName, String valueName) {
        try {
            connectToServer = setConnection();
            statement = connectToServer.createStatement();
            statement.executeUpdate("INSERT INTO " + tableName + " VALUES (" + valueName + ")");
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public static void executeUpdateQuerys(String tableAndcolumnName, String newValue, long id) {
        try {
            connectToServer = setConnection();
            statement = connectToServer.createStatement();
            statement.executeUpdate("UPDATE " + tableAndcolumnName + " = '" + newValue + "' WHERE ID = " + id + "");
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}

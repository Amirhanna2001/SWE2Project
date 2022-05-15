package Model;

import java.sql.*;

import static Model.Connectsql.setConnection;

public class SQLSelectQuerys {
           
    public static ResultSet executeSelectQueryWithoutCondition(String colnomName, String tableName){
            ResultSet resultSetFromTable = null;
            try{
                Connection connectToServer = setConnection();
                Statement statement = connectToServer.createStatement();
                resultSetFromTable = statement.executeQuery("SELECT "+colnomName+" FROM "+tableName);
            }catch (SQLException exception){
                System.out.println(exception.getMessage());
            }
            return resultSetFromTable;    
        }

    public static ResultSet executeSelectQueryWithCondition(String colnomName, String tableName, String condition){
        ResultSet resultSetFromTable = null;
        try{
            Connection connectToServer = setConnection();
            Statement statement = connectToServer.createStatement();
            resultSetFromTable = statement.executeQuery("SELECT "+colnomName+" FROM "+tableName +" WHERE " +condition);
            resultSetFromTable.next();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return resultSetFromTable;    
    }

    public static ResultSet executeSelectQueryLimitaion(String tableName){
        ResultSet resultSetFromTable = null;
        try{
            Connection connectToServer = setConnection();
            Statement statement = connectToServer.createStatement();
            resultSetFromTable = statement.executeQuery("SELECT * FROM "+tableName + " limit 1");
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return resultSetFromTable;    
    }

}

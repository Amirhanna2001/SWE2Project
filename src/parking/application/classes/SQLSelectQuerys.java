package parking.application.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static parking.application.classes.Connectsql.setConnection;

public class SQLSelectQuerys {
    
           
    public ResultSet executeSelectQueryWithoutCondition(String colnomName, String tableName){
            ResultSet resultSet = null;
            try{
                Connection connection = setConnection();
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT "+colnomName+" FROM "+tableName);
            }catch (SQLException execption){
                System.out.println(execption.getMessage());
            }
            return resultSet;    

        }

    public ResultSet executeSelectQueryWithCondition(String colnomName, String tableName, int IDNumber){
            ResultSet resultSet = null;
            try{
                Connection connection = setConnection();
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT "+colnomName+" FROM "+tableName +" WHERE id=" +IDNumber);
                resultSet.next();
            }catch (SQLException execption){
                System.out.println(execption.getMessage());
            }
            return resultSet;    
        }

    public ResultSet executeSelectQueryLimitaion(String tableName){
            ResultSet resultSet = null;
            try{
                Connection connection = setConnection();
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM "+tableName + " limit 1");
            }catch (SQLException execption){
                System.out.println(execption.getMessage());
            }
            return resultSet;    

        }

    }

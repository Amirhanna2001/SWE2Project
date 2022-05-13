package parking.application.classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static parking.application.classes.Connectsql.setConnection;

public class SQLUpdateQuerys {
    
   public void executeInsertQuery(String tableName , String valuesName){
        try{
            java.sql.Connection connection = setConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO "+tableName+" VALUES (" + valuesName +")");
            
        }catch(SQLException execption){
            System.out.println(execption);
        }
    }
   
   public void executeUpdateQuerys(String tableAndColnomName , String newValue , long id){
      try{ 
        Connection connection = setConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE " + tableAndColnomName +" = '" + newValue + "' WHERE ID = " + id +"");
      }catch(SQLException execption){
          System.out.println(execption);
      }
   }
}

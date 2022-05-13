package parking.application.classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static parking.application.classes.Connectsql.setConnection;

public class SQLUpdateQuerys {
    
   public void executeInsertQuery(String tableName , String valueName){
        try{
            java.sql.Connection con = setConnection();
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO "+tableName+" VALUES (" + valueName +")");            
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
   
   public void executeUpdateQuerys(String tableAndColnomName , String newValue , long id){
      try{  
        Connection con = setConnection();
        Statement st = con.createStatement();
        st.executeUpdate("UPDATE " + tableAndColnomName +" = '" + newValue + "' WHERE ID = " + id +"");
      }catch(SQLException ex){
          System.out.println(ex);
      }
   }
    
}

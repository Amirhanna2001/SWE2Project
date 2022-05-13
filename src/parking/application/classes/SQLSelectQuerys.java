package parking.application.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static parking.application.classes.Connectsql.setConnection;

public class SQLSelectQuerys {
           
    public ResultSet executeSelectQueryWithoutCondition(String colnomName, String tableName){
            ResultSet rs = null;
            try{
                Connection con = setConnection();
                Statement st = con.createStatement();
                rs = st.executeQuery("SELECT "+colnomName+" FROM "+tableName);
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            return rs;    
        }

    public ResultSet executeSelectQueryWithCondition(String colnomName, String tableName, String condition){
        ResultSet rs = null;
        try{
            Connection con = setConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT "+colnomName+" FROM "+tableName +" WHERE " +condition);
            rs.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return rs;    
    }

    public ResultSet executeSelectQueryLimitaion(String tableName){
        ResultSet rs = null;
        try{
            Connection con = setConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM "+tableName + " limit 1");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return rs;    
    }

}

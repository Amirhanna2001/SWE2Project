package parking.application.classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static parking.application.classes.Connectsql.setConnection;

public class SQLDeleteQuerys {
    
    public void executeDeleteQuery(String tableName , String condition ){
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM "+tableName+" WHERE "+condition);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}

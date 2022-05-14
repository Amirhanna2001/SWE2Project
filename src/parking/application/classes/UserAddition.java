package parking.application.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static parking.application.classes.Connectsql.setConnection;

public class UserAddition {
    SQLSelectQuerys e = new SQLSelectQuerys();
    SQLUpdateQuerys i = new SQLUpdateQuerys();

    public int getID(String TableName) {
        int k = 2000;
        int i = 0;
        try {
            ResultSet rs = e.executeSelectQueryWithoutCondition("id", TableName);
            while (rs.next()) {
                i = rs.getInt("id");
                k++;
            }if (k == 2000) {
                i = 2000;
            }
            rs.close();
          
        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }
        return i + 1;
    }
      
    public void updatePlate(String p, int id) {
        try {
            i.executeUpdateQuerys("parkedcar set platenum", p, id);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void setStartTime(String TableName, long id) {
         
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String time = dateFormat.format(date);
        
        try {
            i.executeUpdateQuerys( TableName+" set starttime",time , id);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void setEndTime(String TableName, long id) {
       
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String time = dateFormat.format(date);
       
        try {
            i.executeUpdateQuerys(TableName + " set endtime",time , id);  
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void setTotalTime(String TableName, long id) {
        Connection con = setConnection();
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE " + TableName + " SET `totaltime`=(SELECT TIMEDIFF(endtime,starttime)) WHERE id=" + id + "");
            //i.executeUpdateQuerys(TableName + " SET `totaltime`" ,"(SELECT TIMEDIFF(endtime,starttime))", id);
            st.close();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
    
  
}

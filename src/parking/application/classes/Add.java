package parking.application.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static parking.application.classes.Connectsql.setConnection;

public class Add {
    SQLSelectQuerys e = new SQLSelectQuerys();
    SQLUpdateQuerys i = new SQLUpdateQuerys();
    
    public void bookaSpot(int id, int spot, String p) throws Exception {
        try {
            
            new Geter().getID("parkedcar");
            setStartTime("parkedcar", id);
            i.executeInsertQuery("parkedcar (spot)", spot+"");
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addSpot() {
        int s, max1 = 0, max2 = 0;
        try {
            ResultSet rs = e.executeSelectQueryWithoutCondition("spot", "parkedcar");
            while (rs.next()) {
                s = rs.getInt("spot");
                if (max1 < s) {
                    max1 = s;
                }
            }
            ResultSet re = e.executeSelectQueryWithoutCondition("spot","freespots");
            
            while (re.next()) {
                s = re.getInt("spot");
                if (max2 < s) {
                    max2 = s;
                }
            }
            if (max2 > max1) {
                max2 += 1;
                i.executeInsertQuery("freespots", max2+"");
            } else {
                max1 += 1;
                i.executeInsertQuery("freespots", max1+"");
            }
        } catch (SQLException ex) {
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
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}

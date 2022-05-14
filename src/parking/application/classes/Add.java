package parking.application.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static parking.application.classes.Connectsql.setConnection;
import static parking.application.classes.SQLUpdateQuerys.executeUpdateQuerys;

public class Add {
    SQLSelectQuerys e = new SQLSelectQuerys();
    SQLUpdateQuerys i = new SQLUpdateQuerys();
    
    public static int maximumSpot(ResultSet rs) throws SQLException {
        int s;
        int max = 0;
        while (rs.next()) {
            s = rs.getInt("spot");
            if (max < s) {
                max = s;
            }
        }
        return max;
    }

    public static void setStartTime(String TableName, long id) {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String time = dateFormat.format(date);

        try {
            executeUpdateQuerys(TableName + " set starttime", time, id);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void setEndTime(String TableName, long id) {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String time = dateFormat.format(date);

        try {
            executeUpdateQuerys(TableName + " set endtime", time, id);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public static void setTotalTime(String TableName, long id) {
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

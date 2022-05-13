package parking.application.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import static parking.application.classes.Connectsql.setConnection;

public class Payment {
    SQLSelectQuerys e = new SQLSelectQuerys();
    SQLUpdateQuerys in =  new SQLUpdateQuerys();
    SQLDeleteQuerys d = new SQLDeleteQuerys();
    
    public void setPayment(String TableName, float p, int id) {
        try {
            in.executeUpdateQuerys(TableName + " set payment", p+"", id);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void pay(int id) throws SQLException {
        //Connection con = setConnection();
        int i = 0, spot = 0;
        String p = null;
        Time st = null, et = null, tt = null;
        float payment = (float) 0.0;
        //Statement stt = con.createStatement();
        try {
            ResultSet rs = e.executeSelectQueryWithCondition("*","parkedcar" , id);
            
            i = rs.getInt("id");
            spot = rs.getInt("spot");
            p = rs.getString("platenum");
            payment = rs.getFloat("payment");
            st = rs.getTime("starttime");
            et = rs.getTime("endtime");
            tt = rs.getTime("totalTime");
            
            in.executeInsertQuery("totalcars",i + "," + spot + ",'" + st + "','" + et + "','" + tt + "','" + p + "','" + payment + "'");
            
            ResultSet hu = e.executeSelectQueryWithCondition("spot","parkedcar" , id);
            int s = hu.getInt("spot");
            
            in.executeInsertQuery("freespots", s+"" );
            
            //stt.executeUpdate("DELETE From parkedcar WHERE id=" + id + "");
            d.executeDeleteQuery("parkedcar", "id =" + id);
            
            //stt.close();
            ///con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


}

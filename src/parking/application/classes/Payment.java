package parking.application.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

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
        int i = 0, spot = 0;
        String p = null;
        Time st = null, et = null, tt = null;
        float payment = (float) 0.0;
        try {
            ResultSet rs = e.executeSelectQueryWithCondition("*","parkedcar" , "id ="+id);
            
            i = rs.getInt("id");
            spot = rs.getInt("spot");
            p = rs.getString("platenum");
            payment = rs.getFloat("payment");
            st = rs.getTime("starttime");
            et = rs.getTime("endtime");
            tt = rs.getTime("totalTime");
            
            in.executeInsertQuery("totalcars",i + "," + spot + ",'" + st + "','" + et + "','" + tt + "','" + p + "','" + payment + "'");
            
            ResultSet hu = e.executeSelectQueryWithCondition("spot","parkedcar" , "id ="+id);
            int s = hu.getInt("spot");
            
            in.executeInsertQuery("freespots", s+"" );
            
            d.executeDeleteQuery("parkedcar", "id =" + id);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


}

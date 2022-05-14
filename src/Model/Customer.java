package Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import static parking.application.classes.Add.setEndTime;
import static parking.application.classes.Add.setStartTime;
import static parking.application.classes.Add.setTotalTime;
import static parking.application.classes.Geter.getTotalTime;
import static parking.application.classes.Payment.setPayment;
import static parking.application.classes.SQLDeleteQuerys.executeDeleteQuery;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryWithCondition;
import static parking.application.classes.SQLUpdateQuerys.executeInsertQuery;

public class Customer {

    public void BookASpot(int id, int spot, String plateNumber) {
        executeInsertQuery("parkedcar (id,spot,platenum)",id +   ","   + spot +   ",'"   + plateNumber+"'");
        setStartTime("parkedcar", id);
    }
    
    public void PayInExitStation(int id) throws SQLException {
        int spot;
        String p;
        Time st, et, tt;
        setEndTime("parkedcar", id);
        setTotalTime("parkedcar", id);
        float payment = calculatePayment(id);
        setPayment("parkedcar", payment, id);
        payment = (float) 0.0;
        ResultSet rs = executeSelectQueryWithCondition("*", "parkedcar", "id =" + id);
        spot = rs.getInt("spot");
        p = rs.getString("platenum");
        payment = rs.getFloat("payment");
        st = rs.getTime("starttime");
        et = rs.getTime("endtime");
        tt = rs.getTime("totalTime");
        executeInsertQuery("totalcars", id + "," + spot + ",'" + st + "','" + et + "','" + tt + "','" + p + "','" + payment + "'");
        ResultSet hu = executeSelectQueryWithCondition("spot", "parkedcar", "id =" + id);
        int s = hu.getInt("spot");
        executeInsertQuery("freespots", s + "");
        executeDeleteQuery("parkedcar", "id =" + id);

    }

    public float calculatePayment(int id) {
        Time t = getTotalTime(id);
        float ttime = (float) ((t.getSeconds() / 3600.0) + (t.getMinutes() / 60.0) + (t.getHours()));
        float payment = (float) (ttime * 5.0);
        return payment;
    }
    
}

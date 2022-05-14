package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static parking.application.classes.Connectsql.setConnection;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryWithCondition;
import static parking.application.classes.SQLUpdateQuerys.executeUpdateQuerys;

public class TimeManagment {

    public static String GetCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date);
        return time;
    }

    public static Time getTotalTime(int id) {
        Time totalTime = null;
        try {
            ResultSet resultSetFromParkedCar = executeSelectQueryWithCondition("totaltime", "parkedcar", "id =" + id);
            totalTime = resultSetFromParkedCar.getTime("totaltime");
            resultSetFromParkedCar.close();

        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }
        return totalTime;
    }

    public static void setStartTime(String TableName, long id) {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String time = dateFormat.format(date);

        try {
            executeUpdateQuerys(TableName + " set startTime", time, id);
        } catch (Exception exceptionError) {
            System.out.println(exceptionError);
        }
    }

    public static void setEndTime(String TableName, long id) {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String time = dateFormat.format(date);

        try {
            executeUpdateQuerys(TableName + " set endtime", time, id);
        } catch (Exception exceptionError) {
            System.out.println(exceptionError);
        }

    }

    public static void setTotalTime(String TableName, long id) {
        Connection connectToServer = setConnection();
        try {
            Statement statement = connectToServer.createStatement();
            statement.executeUpdate("UPDATE " + TableName + " SET `totaltime`=(SELECT TIMEDIFF(endtime,starttime)) WHERE id=" 
                    + id + "");
            statement.close();
            connectToServer.close();
        } catch (Exception exceptionError) {
            System.out.println(exceptionError);
        }

    }
}

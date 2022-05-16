package Controller;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static Model.Connectsql.setConnection;
import static Model.SQLQueries.*;

public class TimeManagment {

    public static Time getTotalTime(int id) {
        Time totalTime = null;
        try {
            ResultSet resultSetFromParkedCar;
            resultSetFromParkedCar = executeSelectQueryWithCondition("totaltime", "parkedcar", "id =" + id);
            totalTime = resultSetFromParkedCar.getTime("totaltime");
            resultSetFromParkedCar.close();
        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }
        return totalTime;
    }

    public static void setStartTime(String TableName, long id) {
        String time = GetCurrentTime();
        executeUpdateQuerys(TableName + " set startTime", time, id);
    }

    public static void setEndTime(String TableName, long id) {
        String time = GetCurrentTime();
        executeUpdateQuerys(TableName + " set endtime", time, id);
    }

    public static void setTotalTime(String TableName, long id) {
        Connection connectToServer = setConnection();
        try {
            Statement statement;
            statement = connectToServer.createStatement();
            setEndTime(TableName,id);
            statement.executeUpdate("UPDATE "+ TableName+ " SET `totaltime`=(SELECT TIMEDIFF(endtime,starttime)) WHERE id="
                    + id + "");
            statement.close();
            connectToServer.close();
        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }

    }
    
    public static String GetCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentDate = new Date();
        String time = dateFormat.format(currentDate);
        return time;
    }
}

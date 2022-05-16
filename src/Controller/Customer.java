package Controller;

import static Controller.TimeManagment.getTotalTime;

import static Model.SQLQueries.executeSelectQueryWithoutCondition;
import static Model.SQLQueries.executeUpdateQuerys;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Customer extends ParkingMangement{
  
    public static void setPayment(String TableName, double payment, int id) {
        executeUpdateQuerys(TableName + " set payment", payment+"", id);
    }
  
    public static int getNextID () {
        int i = 2000;
        try {
            ResultSet rs = executeSelectQueryWithoutCondition("id", "parkedcar");
            while (rs.next()) {
                i = rs.getInt("id");
            }         
            rs.close();
        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }
        return i + 1;
    }
    public static double calculateTotalPayment(int id) {
        Time totalTimeOfParking = getTotalTime(id);
        double totalTimeInDecimal = ((totalTimeOfParking.getSeconds() / 3600.0) + (totalTimeOfParking.getMinutes() / 60.0) + (totalTimeOfParking.getHours()));
        double totalPaymentValue =  (totalTimeInDecimal * 5.0);
        return totalPaymentValue;
    }
}

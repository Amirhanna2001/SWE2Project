package Controller;
import static Controller.TimeManagment.*;
import static Model.SQLQueries.executeSelectQueryWithoutCondition;
import static Model.SQLQueries.executeUpdateQuerys;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Customer extends ParkingMangement{
  
    public static void setPayment(String TableName, double payment, int id) {
        executeUpdateQuerys(TableName + " set payment", payment+"", id);
    }
  
    public static double calculateTotalPayment(int id) {
        Time totalTimeOfParking = getTotalTime(id);
        double totalTimeInDecimal = calculateTotalTimeInDecimal(totalTimeOfParking);
        double totalPaymentValue =  (totalTimeInDecimal * 5.0);
        return totalPaymentValue;
    }
    
    public static int getNextID () {
        int i = 2000;
        try {
            ResultSet resultSet = executeSelectQueryWithoutCondition("id", "parkedcar");
            while (resultSet.next()) {
                i = resultSet.getInt("id");
            }         
            resultSet.close();
        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }
        return i + 1;
    }
    
   
}

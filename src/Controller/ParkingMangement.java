package Controller;

import static Controller.Customer.getNextID;
import static Controller.SpotsModification.getRightSpot;
import java.sql.*;
import static Controller.TimeManagment.*;
import static Model.SQLQueries.*;

public abstract class ParkingMangement {

    public static void deleteUSerDataById(int id) throws SQLException {
        executeDeleteQuery("parkedcar", "id = "+id);
    }

    public static void addItemToDataBase(String plateNumber) {
        String time = getStartTime();
        executeInsertQuery("parkedcar (id,spot,platenum,starttime)", getNextID()+","+getRightSpot()+",'"+plateNumber+"','"+time+"'");
        //setStartTime("parkedcar", getNextID());
        
    }

    public static void translateSpotDataToFreeSpots(int id) throws SQLException {
        ResultSet resultSetFromParkedCar = executeSelectQueryWithCondition("spot", "parkedcar", "id = "+id);
        int spot = resultSetFromParkedCar.getInt("spot");
        executeInsertQuery("freespots", spot+"");
    }

    public static void translateDataToTotalCar(int id) throws SQLException {
        ResultSet resultSet = executeSelectQueryWithCondition("*", "parkedcar", "id = " + id);
        int spot = resultSet.getInt("spot");
        String plateNumber = resultSet.getString("platenum");
        double totalPaymentOfParking = resultSet.getFloat("payment");
        Time startTime = resultSet.getTime("starttime");
        Time endTime = resultSet.getTime("endtime");
        Time totalTime = resultSet.getTime("totalTime");
        executeInsertQuery("totalcars", id + "," + spot + ",'" + startTime + "','" + endTime + "','" + totalTime + "','" +
                plateNumber + "','" + totalPaymentOfParking + "'");
    }
    
    public boolean isExistInDatabase(int value,String column,String table) {
        boolean check = false;
        try {
            ResultSet resultSet = executeSelectQueryWithoutCondition(column, table);
            check = checkIntegerIsExist(resultSet,value ,column);
            resultSet.close();
        } catch (SQLException expression) {
            System.out.println(expression);
        }
        return check;
    }

    public boolean checkIntegerIsExist(ResultSet rs, int value , String column){
        int dataBaseValue = 0;
        boolean check = false;    
        try {
            while (rs.next()) {
                dataBaseValue = rs.getInt(column);
                if (dataBaseValue == value) {
                    check = true;
                    break;
                }
            }
        } catch (SQLException expression) {
            System.out.println(expression);
        }
        return check ;
    }
   
    public boolean isExistPlateNumber(String plateNumber) {
        boolean check =false;
        try {
            ResultSet resultSet = executeSelectQueryWithoutCondition("platenum", "parkedcar");
            check = checkStringIsExist(resultSet ,plateNumber);
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return check;
    }
    
    public boolean checkStringIsExist(ResultSet rs,String plateNumber){
        String dataBasevalue  ;
        boolean check = false;    
        try {
            while (rs.next()) {
                dataBasevalue = rs.getString("platenum");
                System.out.println(dataBasevalue);
                if (dataBasevalue.equals(plateNumber)) {
                    check = true;
                    break;
                }
            }
        } catch (SQLException expression) {
            System.out.println(expression);
        }
        return check ;
    }
}
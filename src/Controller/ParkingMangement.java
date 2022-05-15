package Controller;

import java.sql.*;
import static Controller.TimeManagment.*;
import Model.Geter;
import static Model.Payment.setPayment;
import static Model.SQLDeleteQuerys.executeDeleteQuery;
import static Model.SQLSelectQuerys.executeSelectQueryWithCondition;
import static Model.SQLSelectQuerys.executeSelectQueryWithoutCondition;
import static Model.SQLUpdateQuerys.executeInsertQuery;

public abstract class ParkingMangement {

    public void PayInExitStation(int id) throws SQLException {
        setEndTime("parkedcar", id);
        setTotalTime("parkedcar", id);
        double totalPaymentOfParking = calculateTotalPayment(id);
        setPayment("parkedcar", totalPaymentOfParking, id);
    }

    public void deleteUSerDataById(int id) throws SQLException {
        executeDeleteQuery("parkedcar", "id =" + id);
    }

    public void addItemToDataBase(String plateNumber) {
        int id = Geter.getID();
        int spot = Geter.getRightSpot();
        executeInsertQuery("parkedcar (id,spot,platenum)", id + "," + spot + ",'" + plateNumber + "'");
        setStartTime("parkedcar", id);
    }

    public void translateSpotDataToFreeSpots(int id) throws SQLException {
        ResultSet resultSetFromParkedCar = executeSelectQueryWithCondition("spot", "parkedcar", "id =" + id);
        int spot = resultSetFromParkedCar.getInt("spot");
        executeInsertQuery("freespots", spot + "");
    }

    public void translateDataToTotalCar(int id) throws SQLException {
        int spot;
        String plateNumber;
        double totalPaymentOfParking;
        Time startTime, endTime, totalTime;
        ResultSet resultSetFromParkedCar = executeSelectQueryWithCondition("*", "parkedcar", "id =" + id);
        spot = resultSetFromParkedCar.getInt("spot");
        plateNumber = resultSetFromParkedCar.getString("platenum");
        totalPaymentOfParking = resultSetFromParkedCar.getFloat("payment");
        startTime = resultSetFromParkedCar.getTime("starttime");
        endTime = resultSetFromParkedCar.getTime("endtime");
        totalTime = resultSetFromParkedCar.getTime("totalTime");
        executeInsertQuery("totalcars", id + "," + spot + ",'" + startTime + "','" + endTime + "','" + totalTime + "','" +
                plateNumber + "','" + totalPaymentOfParking + "'");
    }

    public double calculateTotalPayment(int id) {
        Time totalTimeOfParking = getTotalTime(id);
        double totalTimeInDecimal = ((totalTimeOfParking.getSeconds() / 3600.0) + (totalTimeOfParking.getMinutes() / 60.0) + (totalTimeOfParking.getHours()));
        double totalPaymentValue =  (totalTimeInDecimal * 5.0);
        return totalPaymentValue;
    }
    
        public static int getMaximumSpot(ResultSet resultSet) throws SQLException {
        int spot;
        int maxSpotNmber = 0;
        while (resultSet.next()) {
            spot = resultSet.getInt("spot");
            if (maxSpotNmber < spot) {
                maxSpotNmber = spot;
            }
        }
        return maxSpotNmber;
    }

    public boolean isExistInDatabase(int value,String column,String table) {
        int dataBaseValue = 0;
        boolean check = false;
        try {
            ResultSet resultSet = executeSelectQueryWithoutCondition(column, table);
            while (resultSet.next()) {
                dataBaseValue = resultSet.getInt(column);
                System.out.println(value);
                if (dataBaseValue == value) {
                    check = true;
                    break;
                }
            }
            resultSet.close();
        } catch (SQLException expression) {
            System.out.println(expression);
        }
        return check;
    }

    public boolean isExistPlateNumber(String plateNumber) {
        String dataBasevalue;
        boolean check = false;
        try {
            ResultSet resultSet = executeSelectQueryWithoutCondition("platenum", "parkedcar");
            while (resultSet.next()) {
                dataBasevalue = resultSet.getString("platenum");
                if (dataBasevalue.equals(plateNumber)) {
                    check = true;
                    break;
                }
            }
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return check;
    }
}
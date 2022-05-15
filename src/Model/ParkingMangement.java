package Model;

import java.sql.*;
import static Model.TimeManagment.*;
import parking.application.classes.Geter;
import static parking.application.classes.Payment.setPayment;
import static parking.application.classes.SQLDeleteQuerys.executeDeleteQuery;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryWithCondition;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryWithoutCondition;
import static parking.application.classes.SQLUpdateQuerys.executeInsertQuery;

public abstract class ParkingMangement {

    public void PayInExitStation(int id) throws SQLException {
        setEndTime("parkedcar", id);
        setTotalTime("parkedcar", id);
        float payment = calculatePayment(id);
        setPayment("parkedcar", payment, id);
    }

    public void deleteUSerDataById(int id) throws SQLException {
        executeDeleteQuery("parkedcar", "id =" + id);
    }

    public void bookASpot(String plateNumber) {
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
        float payment;
        Time startTime, endTime, totalTime;
        ResultSet resultSetFromParkedCar = executeSelectQueryWithCondition("*", "parkedcar", "id =" + id);
        spot = resultSetFromParkedCar.getInt("spot");
        plateNumber = resultSetFromParkedCar.getString("platenum");
        payment = resultSetFromParkedCar.getFloat("payment");
        startTime = resultSetFromParkedCar.getTime("starttime");
        endTime = resultSetFromParkedCar.getTime("endtime");
        totalTime = resultSetFromParkedCar.getTime("totalTime");
        executeInsertQuery("totalcars", id + "," + spot + ",'" + startTime + "','" + endTime + "','" + totalTime + "','" +
                plateNumber + "','" + payment + "'");
    }

    public float calculatePayment(int id) {
        Time time = getTotalTime(id);
        float totalTime = (float) ((time.getSeconds() / 3600.0) + (time.getMinutes() / 60.0) + (time.getHours()));
        float payment = (float) (totalTime * 5.0);
        return payment;
    }
    
        public static int maximumSpot(ResultSet resultSet) throws SQLException {
        int spot;
        int maxSpot = 0;
        while (resultSet.next()) {
            spot = resultSet.getInt("spot");
            if (maxSpot < spot) {
                maxSpot = spot;
            }
        }
        return maxSpot;
    }

    public boolean isExist(int value,String column,String table) {
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
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return check;
    }
}
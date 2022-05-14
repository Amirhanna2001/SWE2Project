package Model;

import java.sql.ResultSet;
import static Model.TimeManagment.*;
import java.sql.SQLException;
import java.sql.Time;
import parking.application.classes.Geter;
import static parking.application.classes.Payment.setPayment;
import static parking.application.classes.SQLDeleteQuerys.executeDeleteQuery;
import static parking.application.classes.SQLDeleteQuerys.executeDeleteQueryLimitaion;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryWithCondition;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryWithoutCondition;
import static parking.application.classes.SQLUpdateQuerys.executeInsertQuery;

public abstract class Car {

    public void PayInExitStation(int id) throws SQLException {

        setEndTime("parkedcar", id);
        setTotalTime("parkedcar", id);
        float payment = calculatePayment(id);
        setPayment("parkedcar", payment, id);
    }

    public void deleteUSerDataById(int id) throws SQLException {
        translateSpotDataToFreeSpots(id);
        executeDeleteQuery("parkedcar", "id =" + id);
    }

    public void bookASpot(String plateNumber) {
        int id = Geter.getID();
        int spot = Geter.getRightSpot();
        executeInsertQuery("parkedcar (id,spot,platenum)", id + "," + spot + ",'" + plateNumber + "'");
        setStartTime("parkedcar", id);
    }

    public void translateSpotDataToFreeSpots(int id) throws SQLException {
        ResultSet hu = executeSelectQueryWithCondition("spot", "parkedcar", "id =" + id);
        int s = hu.getInt("spot");
        executeInsertQuery("freespots", s + "");
    }

    public void translateDataToTotalCar(int id) throws SQLException {
        int spot;
        String plateNumber;
        float payment;
        Time startTime, endTime, totalTime;
        ResultSet resultSet = executeSelectQueryWithCondition("*", "parkedcar", "id =" + id);
        spot = resultSet.getInt("spot");
        plateNumber = resultSet.getString("platenum");
        payment = resultSet.getFloat("payment");
        startTime = resultSet.getTime("starttime");
        endTime = resultSet.getTime("endtime");
        totalTime = resultSet.getTime("totalTime");
        executeInsertQuery("totalcars", id + "," + spot + ",'" + startTime + "','" + endTime + "','" + totalTime + "','" + plateNumber + "','" + payment + "'");
    }

    public float calculatePayment(int id) {
        Time time = getTotalTime(id);
        float totalTime = (float) ((time.getSeconds() / 3600.0) + (time.getMinutes() / 60.0) + (time.getHours()));
        float payment = (float) (totalTime * 5.0);
        return payment;
    }
    
    public static void deleteFirstFreeSpot() {
        try {
            executeDeleteQueryLimitaion("freespots");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
        public static int maximumSpot(ResultSet rs) throws SQLException {
        int s;
        int max = 0;
        while (rs.next()) {
            s = rs.getInt("spot");
            if (max < s) {
                max = s;
            }
        }
        return max;
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

package Controller;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import static Model.SQLQueries.*;

public class Admin extends ParkingMangement {

    public void updatePlateNumberByID(String plateNumber, int id) {
        executeUpdateQuerys("parkedcar set platenum", plateNumber, id);
    }

    public void deleteUserDataBySpot(int spot) {
        executeDeleteQuery("parkedcar", "spot = " + spot);
    }
    
    public static void retakeSpot(int spot){
        executeInsertQuery("freespots", spot + "");
    }

    public void viewParkedCarsReport(DefaultTableModel defaultTableModel) {
        try {
            ResultSet resultSetFromParkedCar = executeSelectQueryWithoutCondition("*", "parkedcar");
            while (resultSetFromParkedCar.next()) {
                defaultTableModel.addRow(new Object[]{resultSetFromParkedCar.getInt("id"), resultSetFromParkedCar.getInt("spot"),
                resultSetFromParkedCar.getTime("starttime"), resultSetFromParkedCar.getString("platenum")});
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public void viewShiftsReportWithPayment(DefaultTableModel dtm) {
        try {
            ResultSet resultSetFromTotalCars =executeSelectQueryWithoutCondition("*", "totalcars");
            while (resultSetFromTotalCars.next()) {
                dtm.addRow(new Object[]{resultSetFromTotalCars.getInt("id"), resultSetFromTotalCars.getInt("spot"),
                    resultSetFromTotalCars.getTime("starttime"), resultSetFromTotalCars.getTime("endtime"), 
                    resultSetFromTotalCars.getTime("totaltime"), resultSetFromTotalCars.getString("platenum"),
                    resultSetFromTotalCars.getFloat("payment")});
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

}
package Model;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import static parking.application.classes.Connectsql.setConnection;
import static parking.application.classes.SQLDeleteQuerys.*;
import static parking.application.classes.SQLUpdateQuerys.*;

public class Admin extends Car {

    public void updatePlateNumberByID(String plateNumber, int id) {
        try {
            executeUpdateQuerys("parkedcar set platenum", plateNumber, id);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void deleteUserDataBySpot(int spot) {
        try {
            executeInsertQuery("freespots", spot + "");
            executeDeleteQuery("parkedcar", "spot = " + spot);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void viewParkedCarsReport(DefaultTableModel defaultTableModel) {
        try {
            Connection connectToServer = setConnection();
            Statement statement = connectToServer.createStatement();
            ResultSet resultSetFromParkedCar = statement.executeQuery("select * from parkedcar");
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
            Connection connectToServer = setConnection();
            Statement statement = connectToServer.createStatement();
            ResultSet resultSetFromTotalCars = statement.executeQuery("select * from totalcars");
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
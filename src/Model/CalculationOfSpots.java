package Model;

import java.sql.*;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryWithoutCondition;

public class CalculationOfSpots {
    
    public int calculateFreeSpots() {
        int counterFreeSpots = 0;
        try {
            ResultSet resultSet;
            resultSet = executeSelectQueryWithoutCondition("*", "freespots");
            while (resultSet.next()) {
                counterFreeSpots++;
            }
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return counterFreeSpots;
    }
    
    public int calculateBusySpots() {
        int counterBusySpots = 0;
        try {
            ResultSet resultSet;
            resultSet = executeSelectQueryWithoutCondition("id", "parkedcar");
            while (resultSet.next()) {
                counterBusySpots++;
            }
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return counterBusySpots;
    }
    
     public int calculateTotalSpots() {
        return calculateBusySpots() + calculateFreeSpots();
    }  
}

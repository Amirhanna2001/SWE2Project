package Controller;

import java.sql.*;
import static Model.SQLSelectQuerys.executeSelectQueryWithoutCondition;

public class CalculationOfSpots {
    
    public int calculateFreeSpots() {
        int numberOfFreeSpots = 0;
        try {
            ResultSet resultSet;
            resultSet = executeSelectQueryWithoutCondition("*", "freespots");
            while (resultSet.next()) {
                numberOfFreeSpots++;
            }
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return numberOfFreeSpots;
    }
    
    public int calculateBusySpots() {
        int numberOfBusySpots = 0;
        try {
            ResultSet resultSet;
            resultSet = executeSelectQueryWithoutCondition("id", "parkedcar");
            while (resultSet.next()) {
                numberOfBusySpots++;
            }
            resultSet.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return numberOfBusySpots;
    }
    
     public int calculateTotalSpots() {
        return calculateBusySpots() + calculateFreeSpots();
    }  
}

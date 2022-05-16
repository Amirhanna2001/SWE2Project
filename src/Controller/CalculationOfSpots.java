package Controller;

import static Model.SQLQueries.executeSelectQueryWithoutCondition;
import java.sql.*;

public class CalculationOfSpots {
    
    public static int calculateSpots(ResultSet resultSet){
        int numberOfSpots = 0;
        try {
            while (resultSet.next()) {
                numberOfSpots++;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return numberOfSpots;
    }
    
    public static int calculateFreeSpots() {
        int numberOfFreeSpots = 0;
        try {
            ResultSet resultSet; 
            resultSet = executeSelectQueryWithoutCondition("*", "freespots");
            numberOfFreeSpots = calculateSpots(resultSet);
            resultSet.close();
        }catch (SQLException exception) {
            System.out.println(exception);
        }
        return numberOfFreeSpots;
    }
    
    public static int calculateBusySpots() {
        int numberOfBusySpots = 0;
        try {
            ResultSet resultSet;
            resultSet = executeSelectQueryWithoutCondition("id", "parkedcar");
            numberOfBusySpots = calculateSpots(resultSet);
            resultSet.close();
        }catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return numberOfBusySpots;
    }
    
     public static int calculateTotalSpots() {
        return calculateBusySpots() + calculateFreeSpots();
    }  
}

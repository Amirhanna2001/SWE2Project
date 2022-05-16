package Controller;

import static Controller.CalculationOfSpots.calculateTotalSpots;
import java.sql.*;
import static Model.SQLQueries.*;
import static Controller.Admin.retakeSpot;

public class SpotsModification {

        
    public void addSpot() {
        int numberOFSpots = calculateTotalSpots() + 1;
        executeInsertQuery("freespots", numberOFSpots + "");
    }

    public void removeSpot() {
        int numberOFSpots = calculateTotalSpots();
        deleteTokenSpot(numberOFSpots);
    }

    public static void makeSpotFree(int id) {
        try {
            ResultSet resultSetFromParkedCar = executeSelectQueryWithCondition("spot", "parkedcar", "id =" + id);
            int spotNumber = resultSetFromParkedCar.getInt("spot");
            retakeSpot(spotNumber);
        }catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public static void deleteTokenSpot(int newSpot) {
        executeDeleteQuery("freespots", "spot = " + newSpot);
    }

    public static void setNewSpot(int newSpot, int id) {
        executeUpdateQuerys("parkedcar set spot", newSpot + "", id);
    }

    public static void deleteFirstFreeSpot() {
        executeDeleteQueryLimitaion("freespots");
    }
    public static int getRightSpot() {
        int rightSpot = 0;
        try {
            ResultSet pc = executeSelectQueryLimitaion("freespots");
            pc.next();
            rightSpot = pc.getInt("spot");
            pc.close();
        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }
        return rightSpot;
    }


}

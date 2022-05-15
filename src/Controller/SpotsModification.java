package Controller;

import java.sql.*;
import static Controller.ParkingMangement.*;
import static Model.SQLDeleteQuerys.executeDeleteQuery;
import static Model.SQLDeleteQuerys.executeDeleteQueryLimitaion;
import static Model.SQLSelectQuerys.executeSelectQueryWithCondition;
import static Model.SQLSelectQuerys.executeSelectQueryWithoutCondition;
import static Model.SQLUpdateQuerys.executeInsertQuery;
import static Model.SQLUpdateQuerys.executeUpdateQuerys;

public class SpotsModification {

    public void addSpot() {
        int maxNumberOfParkedCar, maxNumberOfFreeSpots;
        try {
            ResultSet resultSetFromParkedCar = executeSelectQueryWithoutCondition("spot", "parkedcar");
            ResultSet resultSetFromFreeSpots = executeSelectQueryWithoutCondition("spot", "freespots");

            maxNumberOfParkedCar = getMaximumSpot(resultSetFromParkedCar);
            maxNumberOfFreeSpots = getMaximumSpot(resultSetFromFreeSpots);

            if (maxNumberOfFreeSpots > maxNumberOfParkedCar) {
                maxNumberOfFreeSpots += 1;
                executeInsertQuery("freespots", maxNumberOfFreeSpots + "");
            } else {
                maxNumberOfParkedCar++;
                executeInsertQuery("freespots", maxNumberOfParkedCar + "");
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public void removeSpot() {
        int spot, maxNumberOfSpots = 0;
        try {
            ResultSet resultSetFromFreeSpots = executeSelectQueryWithoutCondition("spot", "freespots");
            while (resultSetFromFreeSpots.next()) {
                spot = resultSetFromFreeSpots.getInt("spot");
                if (maxNumberOfSpots < spot) {
                    maxNumberOfSpots = spot;
                }
            }
            executeDeleteQuery("freespots", "spot = " + maxNumberOfSpots);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public void updateSpotNumberByID(int id, int spot) {
        try {
            ResultSet resultSetFromParkedCar = executeSelectQueryWithCondition("spot", "parkedcar", "id =" + id);
            int spotNumber = resultSetFromParkedCar.getInt("spot");

            executeInsertQuery("freespots", spotNumber + "");
            executeUpdateQuerys("parkedcar set spot", spot + "", id);
            executeDeleteQuery("freespots", "spot = " + spot);

        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public static void deleteFirstFreeSpot() {
        try {
            executeDeleteQueryLimitaion("freespots");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}

package Model;

import java.sql.*;
import static Model.ParkingMangement.maximumSpot;
import static parking.application.classes.SQLDeleteQuerys.executeDeleteQuery;
import static parking.application.classes.SQLDeleteQuerys.executeDeleteQueryLimitaion;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryWithCondition;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryWithoutCondition;
import static parking.application.classes.SQLUpdateQuerys.executeInsertQuery;
import static parking.application.classes.SQLUpdateQuerys.executeUpdateQuerys;

public class SpotsModification {

    public void addSpot() {
        int maxParkedCar, maxFreeSpots;
        try {
            ResultSet resultSetFromParkedCar = executeSelectQueryWithoutCondition("spot", "parkedcar");
            ResultSet resultSetFromFreeSpots = executeSelectQueryWithoutCondition("spot", "freespots");

            maxParkedCar = maximumSpot(resultSetFromParkedCar);
            maxFreeSpots = maximumSpot(resultSetFromFreeSpots);

            if (maxFreeSpots > maxParkedCar) {
                maxFreeSpots += 1;
                executeInsertQuery("freespots", maxFreeSpots + "");
            } else {
                maxParkedCar += 1;
                executeInsertQuery("freespots", maxParkedCar + "");
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public void removeSpot() {
        int spot, maxSpot = 0;
        try {
            ResultSet resultSetFromFreeSpots = executeSelectQueryWithoutCondition("spot", "freespots");
            while (resultSetFromFreeSpots.next()) {
                spot = resultSetFromFreeSpots.getInt("spot");
                if (maxSpot < spot) {
                    maxSpot = spot;
                }
            }
            executeDeleteQuery("freespots", "spot = " + maxSpot);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    public void updateSpotNumberByID(int id, int spot) {
        try {
            ResultSet resultSetFromParkedCar = executeSelectQueryWithCondition("spot", "parkedcar", "id =" + id);
            int s = resultSetFromParkedCar.getInt("spot");

            executeInsertQuery("freespots", s + "");
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

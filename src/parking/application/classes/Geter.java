package parking.application.classes;

import java.sql.*;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryLimitaion;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryWithoutCondition;

public class Geter {

    SQLSelectQuerys e = new SQLSelectQuerys();

    public static int getRightSpot() {
        int x = 0;
        try {
            ResultSet pc = executeSelectQueryLimitaion("freespots");
            pc.next();
            x = pc.getInt("spot");
            pc.close();

        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }
        return x;
    }

    public static int getID () {
        int i = 2000;
        try {
            ResultSet rs = executeSelectQueryWithoutCondition("id", "parkedcar");
            while (rs.next()) {
                i = rs.getInt("id");
            }         
            rs.close();
        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }
        return i + 1;
    }
}

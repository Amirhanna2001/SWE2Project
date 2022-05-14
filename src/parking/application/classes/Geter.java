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
            while (pc.next()) {
                x = pc.getInt("spot");
            }
            pc.close();

        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }
        return x;
    }

    public static int getID () {
        int k = 2000;
        int i = 0;
        try {
            ResultSet rs = executeSelectQueryWithoutCondition("id", "parkedcar");
            while (rs.next()) {
                i = rs.getInt("id");
                k++;
            }
            if (k == 2000) {
                i = 2000;
            }
            rs.close();

        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }
        return i + 1;
    }
}

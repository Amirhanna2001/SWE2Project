package parking.application.classes;

import java.sql.*;

public class Update {
    SQLSelectQuerys e = new SQLSelectQuerys();
    SQLUpdateQuerys i = new SQLUpdateQuerys();
    SQLDeleteQuerys d = new SQLDeleteQuerys();
    public void updateSpot(int id, int spot) {
        try {
            ResultSet rs = e.executeSelectQueryWithCondition("spot","parkedcar", id);
            int s = rs.getInt("spot");
            
            i.executeInsertQuery("freespots",s+"");
            i.executeUpdateQuerys("parkedcar set spot", spot + "",id); 
            d.executeDeleteQuery("freespots", "spot = " +spot);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void updatePlate(String p, int id) {
        try {
            i.executeUpdateQuerys("parkedcar set platenum",p, id);
        }catch (Exception ex) {
            System.out.println(ex);
        }
    }


}

package parking.application.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Delete {
      
    SQLSelectQuerys e = new SQLSelectQuerys();
    SQLUpdateQuerys i =  new SQLUpdateQuerys();
    SQLDeleteQuerys d = new SQLDeleteQuerys();
    
    public void removeSpot() {
        int s, max = 0;
        try {
            ResultSet rs = e.executeSelectQueryWithoutCondition("spot", "freespots");
            while (rs.next()) {
                s = rs.getInt("spot");
                if (max < s) {
                    max = s;
                }
            }
            d.executeDeleteQuery("freespots", "spot = " + max);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteSpot() {
        try {
            d.executeDeleteQueryLimitaion("freespots");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void deleteRow(int id) {
        try {
            ResultSet rs = e.executeSelectQueryWithCondition("spot", "parkedcar", "id ="+id);
            int ss = rs.getInt("spot");
            i.executeInsertQuery("freespots", ss+"");
            d.executeDeleteQuery("parkedcar","id = "+id);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void deleteRows(int s) {
        try {
            i.executeInsertQuery("freespots", s+"");    
            d.executeDeleteQuery("parkedcar", "spot = "+s);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}

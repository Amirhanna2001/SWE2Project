package parking.application.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import static parking.application.classes.SQLDeleteQuerys.executeDeleteQuery;
import static parking.application.classes.SQLDeleteQuerys.executeDeleteQueryLimitaion;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryWithCondition;
import static parking.application.classes.SQLSelectQuerys.executeSelectQueryWithoutCondition;
import static parking.application.classes.SQLUpdateQuerys.executeInsertQuery;

public class Delete {
      
    SQLSelectQuerys e = new SQLSelectQuerys();
    SQLUpdateQuerys i =  new SQLUpdateQuerys();
    SQLDeleteQuerys d = new SQLDeleteQuerys();
    
    
    
    public void deleteFirstFreeSpot() {
        try {
            executeDeleteQueryLimitaion("freespots");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void deleteRow(int id) {
        try {
            ResultSet rs = executeSelectQueryWithCondition("spot", "parkedcar", "id ="+id);
            int ss = rs.getInt("spot");
            executeInsertQuery("freespots", ss+"");
            executeDeleteQuery("parkedcar","id = "+id);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}

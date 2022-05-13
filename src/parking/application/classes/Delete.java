package parking.application.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static parking.application.classes.Connectsql.setConnection;

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
            Connection con = setConnection();
            Statement st = con.createStatement();

            st.executeUpdate("DELETE FROM freespots limit 1 ");
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void deleteRow(int id) {
        try {
            
            ResultSet rs = e.executeSelectQueryWithCondition("spot", "parkedcar", id);
            int ss = rs.getInt("spot");
            
            i.executeInsertQuery("freespots", ss+"");
            d.executeDeleteQuery("parkedcar","id = "+id);
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void deleteRow(String p) {
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT spot from parkedcar where platenum='" + p + "'");
            rs.next();
            int ss = rs.getInt("spot");
            
            i.executeInsertQuery("freespots", ss+"");
            
            st.executeUpdate("DELETE FROM parkedcar WHERE platenum='" + p + "'");
            
            //d.executeDeleteQuery("parkedcar", "platenum = " +p);
            st.close();
            con.close();
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

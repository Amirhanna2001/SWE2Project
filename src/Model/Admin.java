package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import static parking.application.classes.Add.maximumSpot;
import static parking.application.classes.Connectsql.setConnection;
import static parking.application.classes.SQLDeleteQuerys.*;
import static parking.application.classes.SQLSelectQuerys.*;
import static parking.application.classes.SQLUpdateQuerys.*;


public class Admin {
    
    public int calculateFreeSpots() {
        int k = 0;
        try {
            ResultSet rs;
            rs = executeSelectQueryWithoutCondition("*","freespots");
            while (rs.next()) {
                k++;
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return k;
    }

    public int calculateBusySpots() {
        int k = 0;
        try {
            ResultSet rs;
            rs = executeSelectQueryWithoutCondition("id","parkedcar");
            while (rs.next()) {
                k++;
            }
            rs.close();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return k;
    }
    
    public int calculateTotalSpots(){
        return calculateBusySpots()+calculateFreeSpots();
    }
    
    public void addSpot() {
        int max1, max2;
        try {
            ResultSet rs = executeSelectQueryWithoutCondition("spot", "parkedcar");
            ResultSet re = executeSelectQueryWithoutCondition("spot","freespots");
            
            max1 = maximumSpot(rs);
            max2 = maximumSpot(re);
            
            if (max2 > max1) {
                max2 += 1;
                executeInsertQuery("freespots", max2+"");
            } else {
                max1 += 1;
                executeInsertQuery("freespots", max1+"");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void removeSpot() {
        int s, max = 0;
        try {
            ResultSet rs = executeSelectQueryWithoutCondition("spot", "freespots");
            while (rs.next()) {
                s = rs.getInt("spot");
                if (max < s) {
                    max = s;
                }
            }
            executeDeleteQuery("freespots", "spot = " + max);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    //Function ADD user
    
    public void updatePlateNumberByID(String p, int id) {
        try {
            executeUpdateQuerys("parkedcar set platenum",p, id);
        }catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updateSpotNumberByID(int id, int spot) {
        try {
            ResultSet rs = executeSelectQueryWithCondition("spot","parkedcar", "id ="+id);
            int s = rs.getInt("spot");
            
            executeInsertQuery("freespots",s+"");
            executeUpdateQuerys("parkedcar set spot", spot + "",id); 
            executeDeleteQuery("freespots", "spot = " +spot);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteUserDataBySpot(int s) {
        try {
            executeInsertQuery("freespots", s+"");    
            executeDeleteQuery("parkedcar", "spot = "+s);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    //Function Delete user by id
    
    public void viewParkedCarsReport(DefaultTableModel dtm) {
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from parkedcar");
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getInt("id"), rs.getInt("spot"), rs.getTime("starttime"), rs.getString("platenum")});
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
    
    public void viewShiftsReportWithPayment(DefaultTableModel dtm) {
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from totalcars");
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getInt("id"), rs.getInt("spot"), rs.getTime("starttime"), rs.getTime("endtime"), rs.getTime("totaltime"), rs.getString("platenum"), rs.getFloat("payment")});
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
    
}

package parking.application.classes;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Checker {
    
    SQLSelectQuerys e = new SQLSelectQuerys();
    
    public int checkFspot(int s) {
        int i = 0;
        int z = 0;
        try {
           ResultSet pc = e.executeSelectQueryWithoutCondition("spot","freespots");
            while (pc.next()) {
                i = pc.getInt("spot"); 
                System.out.println(i);
                if (i == s) {
                    z = 1;
                    break;
                } else {
                    z = 0;
                }
            }
            pc.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return z;
    }
    
    public int checkPlate(String p) {
        String x = null;
        int z = 0;
        try {
           ResultSet pc = e.executeSelectQueryWithoutCondition("platenum","parkedcar");
            while (pc.next()) {
                x = pc.getString("platenum");
                if (x.equals(p)) {
                    z = 1;
                    break;
                } else {
                    z = 0;
                }
            }
            pc.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return z;
    }

    public int checkId(int id) {
        int i = 0;
        int z = 0;
        try {
             ResultSet pc = e.executeSelectQueryWithoutCondition("id","parkedcar");
            while (pc.next()) {
                i = pc.getInt("id");
                System.out.println(i);
                if (i == id) {
                    z = 1;
                    break;
                } else {
                    z = 0;
                }
            }
            pc.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return z;
    }

    public int freeSpots() {
        int k = 0;
        try {
            ResultSet rs = e.executeSelectQueryWithoutCondition("*","freespots");
            while (rs.next()) {
                k++;
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return k;
    }

    public int busySpots() {
        int k = 0;
        try {
            ResultSet rs = e.executeSelectQueryWithoutCondition("id","parkedcar");
            while (rs.next()) {
                k++;
            }
            rs.close();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return k;
    }

}

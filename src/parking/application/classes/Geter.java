package parking.application.classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Geter {

    SQLSelectQuerys e = new SQLSelectQuerys();
    
    public Time getTotalTime( int id) {   
        Time totalTime = null;
        try {
            ResultSet rs = e.executeSelectQueryWithCondition("totaltime", "parkedcar", "id ="+id);
            totalTime = rs.getTime("totaltime");
            rs.close();
            
        }catch (SQLException exceptionError) {
           System.out.println(exceptionError);
        }
        return totalTime;
    }
  
    public int getSpot(String tableName) {
        int x = 0;
        try {
            ResultSet pc = e.executeSelectQueryLimitaion(tableName);
            while (pc.next()) {
                x = pc.getInt("spot");
            }
            pc.close();
       
        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }
        return x;
    }
    
    public int getID(String TableName) {
        int k = 2000;
        int i = 0;
        try {
            ResultSet rs = e.executeSelectQueryWithoutCondition("id", TableName);
            while (rs.next()) {
                i = rs.getInt("id");
                k++;
            }if (k == 2000) {
                i = 2000;
            }
            rs.close();
          
        } catch (SQLException exceptionError) {
            System.out.println(exceptionError);
        }
        return i + 1;
    }
}

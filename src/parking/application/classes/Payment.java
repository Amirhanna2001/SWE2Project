package parking.application.classes;

import static parking.application.classes.SQLUpdateQuerys.executeUpdateQuerys;

public class Payment {
    SQLSelectQuerys e = new SQLSelectQuerys();
    SQLUpdateQuerys in =  new SQLUpdateQuerys();
    SQLDeleteQuerys d = new SQLDeleteQuerys();
    
    public static void setPayment(String TableName, float p, int id) {
        try {
            executeUpdateQuerys(TableName + " set payment", p+"", id);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
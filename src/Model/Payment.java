package Model;

import static Model.SQLUpdateQuerys.executeUpdateQuerys;

public class Payment {
    
    public static void setPayment(String TableName, double payment, int id) {
        try {
            executeUpdateQuerys(TableName + " set payment", payment+"", id);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

}
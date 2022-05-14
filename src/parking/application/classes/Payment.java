package parking.application.classes;

import static parking.application.classes.SQLUpdateQuerys.executeUpdateQuerys;

public class Payment {
    
    public static void setPayment(String TableName, float payment, int id) {
        try {
            executeUpdateQuerys(TableName + " set payment", payment+"", id);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

}
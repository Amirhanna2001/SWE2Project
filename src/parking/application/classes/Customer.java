package parking.application.classes;
import java.sql.Time;

public class Customer {
    int id,spot;
    String plateNumber;
    float payment;
    public Customer(int id, int spot, String plateNumber) {
        this.id = id;
        this.spot = spot;
        this.plateNumber = plateNumber;
    }
    public void CustomerLeaving(float payment) {
        this.payment=payment;
    }
    
}

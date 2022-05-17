package Controller;

import static Controller.Customer.calculateTotalPayment;
import static Controller.Customer.setPayment;
import static Controller.ParkingMangement.deleteUSerDataById;
import static Controller.ParkingMangement.translateDataToTotalCar;
import static Controller.ParkingMangement.translateSpotDataToFreeSpots;
import static Controller.TimeManagment.setTotalTime;
import java.sql.SQLException;


/**
 *
 * @author Marina_
 */
public class CashPayment implements Payment {


    @Override
    public void pay(int id) {
        try {
            setTotalTime("parkedcar", id);
            double payment = calculateTotalPayment(id);
            setPayment("parkedcar", payment, id);
            translateDataToTotalCar(id);
            translateSpotDataToFreeSpots(id);
            deleteUSerDataById(id);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}

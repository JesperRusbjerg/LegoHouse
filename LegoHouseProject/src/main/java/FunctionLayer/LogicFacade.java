package FunctionLayer;

import DBAccess.StorageFacade;
import java.util.List;

/**
 * The purpose of LogicFacade is to...
 *
 * @author kasper
 */
public class LogicFacade {

    // COMES FROM DATAMAPPER
    public static User login(String email, String password) throws LegoHouseException {
        return StorageFacade.login(email, password);
    }

    public static User createUser(String email, String password) throws LegoHouseException {
        return StorageFacade.createUser(email, password);
    }

    public static Order createOrder(int userID, int length, int width, int height) throws LegoHouseException{
        return StorageFacade.createOrder(userID, length, width, height);
    }

    public static List<Order> userOrders(int userID) throws LegoHouseException {
        return StorageFacade.userOrders(userID);
    }

    public static Order orderDetail(int orderID) throws LegoHouseException {
        return StorageFacade.orderDetail(orderID);
    }

    public static List<Order> allOrders() throws LegoHouseException {
        return StorageFacade.allOrders();
    }

    public static void sendOrder(int orderID) throws LegoHouseException {
        StorageFacade.sendOrder(orderID);
    }

    public static House Calculator(int userLength, int userWidth, int userHeight) throws LegoHouseException {

        BrickCalculator b = new BrickCalculator();
        return b.Calculator(userLength, userWidth, userHeight);

    }

//    public static void insertOrderItem(int orderID, int brickID, int quantity) throws OrderException{
//             StorageFacade.insertOrderItem(orderID, brickID, quantity);
//             }
//        public static Brick getBrick(int brickID) throws OrderException, SQLException {
//                      return StorageFacade.getBrick(brickID);
//                      }
}

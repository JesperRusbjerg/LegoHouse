package FunctionLayer;

import DBAccess.StorageFacade;
import java.util.List;

/**
 * The purpose of the LogicFacade is to take a Method from the StorageFacade and add some logic to it.
 * This Facade also ensures that no logic is written in the Servlets, all Logic should be called in this class.
 * The Logic facade gets called in the Servlets.
 * @author Jesper
 */


public class LogicFacade {

    
    /**
     * returns method from  {@link DBAccess.UserMapper#login(java.lang.String, java.lang.String) }.
     * @param email
     * @param password
     * @return
     * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
     */
    public static User login(String email, String password) throws LegoHouseException {
        return StorageFacade.login(email, password);
    }
    
    /**
     * Creates User object with standard Customer for the StorageFacade and DB to use.
     * @param email
     * @param password
     * @return
     * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
     */

    public static User createUser(String email, String password) throws LegoHouseException {
        User user = new User(email, password, "customer");
    return StorageFacade.createUser(user);
    }

    /**
     * returns method from  {@link DBAccess.UserMapper#createOrder(int, int, int, int) }.
     * @param userID
     * @param length
     * @param width
     * @param height
     * @return
     * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
     */
    
    public static Order createOrder(int userID, int length, int width, int height) throws LegoHouseException{
        return StorageFacade.createOrder(userID, length, width, height);
    }
    
    /**
     * returns method from  {@link DBAccess.UserMapper#userOrders(int)  }.
     * @param userID
     * @return
     * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
     */

    public static List<Order> userOrders(int userID) throws LegoHouseException {
        return StorageFacade.userOrders(userID);
    }
    
    /**
     * returns method from  {@link DBAccess.UserMapper#orderDetail(int)  }.
     * @param orderID
     * @return
     * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
     */

    public static Order orderDetail(int orderID) throws LegoHouseException {
        return StorageFacade.orderDetail(orderID);
    }
    
   /**
    * returns method from  {@link DBAccess.UserMapper#allOrders()  }.
    * @return
    * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
    */

    public static List<Order> allOrders() throws LegoHouseException {
        return StorageFacade.allOrders();
    }
    
    /**
     * returns method from  {@link DBAccess.UserMapper#sendOrder(int)  }.
     * @param orderID
     * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
     */

    public static void sendOrder(int orderID) throws LegoHouseException {
        StorageFacade.sendOrder(orderID);
    }

    
    /**
     * Calculates House object from User input and returns it.
     * @param userLength not null.
     * @param userWidth not null.
     * @param userHeight not null.
     * @return Object of type House.
     */
     
    public static House Calculator(int userLength, int userWidth, int userHeight) {

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

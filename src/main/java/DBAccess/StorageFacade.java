package DBAccess;

import FunctionLayer.LegoHouseException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.util.List;

/**
 * The StorageFacade returns from the DB and passes them onto the LogicFacade.
 * No logic is allowed in the StorageFacade.
 * @author Jesper
 */

public class StorageFacade {
    
    /**
     * is described in UserMapper: {@link  UserMapper#login(java.lang.String, java.lang.String) }
     * @param email
     * @param password
     * @return
     * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
     */

    public static User login(String email, String password) throws LegoHouseException {
        return UserMapper.login(email, password);
    }

    
    /**
     * is described in UserMapper: {@link  UserMapper#createUser(FunctionLayer.User)  }
     * @param user
     * @return
     * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
     */
    public static User createUser(User user) throws LegoHouseException {
        UserMapper.createUser(user);
        return user;
    }

    /**
     * is described in UserMapper: {@link  UserMapper#createOrder(int, int, int, int)  }
     * @param userID
     * @param length
     * @param width
     * @param height
     * @return
     * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
     */
    public static Order createOrder(int userID, int length, int width, int height) throws LegoHouseException {
        Order o = UserMapper.createOrder(userID, length, width, height);
        return o;
    }
    
    /**
     * is described in UserMapper: {@link  UserMapper#userOrders(int)  }
     * @param userID
     * @return
     * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
     */
   
    public static List<Order> userOrders(int userID) throws LegoHouseException{
        return UserMapper.userOrders(userID);
    }

    /**
     * is described in UserMapper: {@link  UserMapper#orderDetail(int)  }
     * @param orderID
     * @return
     * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
     */
    public static Order orderDetail(int orderID) throws LegoHouseException {
        return UserMapper.orderDetail(orderID);
    }
    
    /**
     * is described in UserMapper: {@link  UserMapper#allOrders()  }
     * @return
     * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
     */
    
        public static List<Order> allOrders() throws LegoHouseException {
        return UserMapper.allOrders();
        }
        
        /**
         * is described in UserMapper: {@link  UserMapper#sendOrder(int)  }
         * @param orderID
         * @throws LegoHouseException throws Exception from UserMapper if there is no connection to DB.
         */
        public static void sendOrder(int orderID) throws LegoHouseException{
        UserMapper.sendOrder(orderID);
        }
        
    
    
//     public static int brickID(int bricksize) throws OrderException, SQLException {
//        return UserMapper.brickID(bricksize);
//    }
////       
//
//    public static void insertOrderItem(int orderID, int brickID, int quantity) throws OrderException {
//        UserMapper.insertOrderItem(orderID, brickID, quantity);
//    }
//
//    public static Brick getBrick(int brickID) throws OrderException, SQLException {
//        return UserMapper.getBrick(brickID);
//    }

}

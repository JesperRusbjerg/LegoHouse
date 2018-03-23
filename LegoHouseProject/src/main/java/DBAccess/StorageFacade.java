package DBAccess;

import FunctionLayer.LegoHouseException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.util.List;

public class StorageFacade {

    public static User login(String email, String password) throws LegoHouseException {
        return UserMapper.login(email, password);
    }

    public static User createUser(String email, String password) throws LegoHouseException {
        User user = new User(email, password, "customer");
        UserMapper.createUser(user);
        return user;
    }

    public static Order createOrder(int userID, int length, int width, int height) throws LegoHouseException {
        Order o = UserMapper.createOrder(userID, length, width, height);
        return o;
    }
   
    public static List<Order> userOrders(int userID) throws LegoHouseException{
        return UserMapper.userOrders(userID);
    }

    public static Order orderDetail(int orderID) throws LegoHouseException {
        return UserMapper.orderDetail(orderID);
    }
    
        public static List<Order> allOrders() throws LegoHouseException {
        return UserMapper.allOrders();
        }
        
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

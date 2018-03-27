package DBAccess;

import FunctionLayer.LegoHouseException;
import FunctionLayer.Order;

import FunctionLayer.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for accessing the database.
 * There are various methods for retrieving data from the tables in the database.
 * @author Jesper
 */


public class UserMapper {
    
    /**
     * 
     * @param email not null
     * @param password not null
     * @return user object, will be used in session, will throw expcetion if not found in DB.
     * @throws LegoHouseException throws Exception if there is no connection to DB.
     */
    
    public static User login( String email, String password ) throws LegoHouseException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT id, role FROM users "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setString( 1, email );
            ps.setString( 2, password );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                String role = rs.getString( "role" );
                int id = rs.getInt( "id" );
                User user = new User( email, password, role );
                user.setId( id );
                return user;
            } else {
                throw new LegoHouseException( "Could not validate user", "index");
            }
        } catch ( ClassNotFoundException | SQLException ex ) {
            throw new LegoHouseException(ex.getMessage(), "index");
        }
    }
    
    /**
     * Register any user into the DB, currently u can register multiple users with same username.
     * @param user Takes in a paramater User, created in the LogicFacade.
     * @throws LegoHouseException throws Exception if there is no connection to DB.
     */
    
    public static void createUser( User user ) throws LegoHouseException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setString( 1, user.getEmail() );
            ps.setString( 2, user.getPassword() );
            ps.setString( 3, user.getRole() );
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt( 1 );
            user.setId( id );
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LegoHouseException( ex.getMessage(), "index" );
        }
    }
    
    /**
     * Creates order and inserts it into DB.
     * @param userID not null.
     * @param length not null.
     * @param width not null.
     * @param height not null.
     * @return returns an order that can be displayed to the user.
     * @throws LegoHouseException throws Exception if there is no connection to DB.
     */
     
    
    public static Order createOrder(int userID, int length, int width, int height) throws LegoHouseException {
        Order o = null;
        try{
            Connection con = Connector.connection();
            String SQL = "INSERT INTO orders (userID, length, width, height) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userID);
            ps.setInt(2, length);
            ps.setInt(3, width);
            ps.setInt(4, height);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt( 1 );
            o = new Order(id, userID, length, width, height, false);
          
        }catch(SQLException | ClassNotFoundException ex){
            throw new LegoHouseException("The world is falling, please contact IT-SUPPORT!", "index");
        }
        return o;
    }
    
    /**
     * Shows a list of all userOrders.
     * @param userID not null
     * @return returns a list of all userOrders.
     * @throws LegoHouseException  throws Exception if there is no connection to DB.
     */
    
     public static List<Order> userOrders(int userID) throws LegoHouseException {
       List<Order> temp = new ArrayList();
         try {
            Connection con = Connector.connection();
            String SQL = "SELECT * from orders where userID = (?)";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setInt( 1, userID );
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                int orderid = rs.getInt("orderID");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                boolean status = rs.getBoolean("status");
                
                Order o = new Order(orderid, userID, length, width, height, status);
                temp.add(o);
            }
        } catch ( ClassNotFoundException | SQLException ex ) {
            throw new LegoHouseException("The world is falling, please contact IT-SUPPORT!", "index");
        }
        return temp;
    }
     
     /**
      * Shows details of selected order.
      * @param orderID not null.
      * @return returns a specific order object.
      * @throws LegoHouseException throws Exception if there is no connection to DB. 
      */
     
    public static Order orderDetail(int orderID) throws LegoHouseException {
       Order o = null;
         try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM orders where orderID = (?)";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setInt( 1, orderID );
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                int orderid  = rs.getInt("orderID");
                int userid = rs.getInt("userID");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                boolean status = rs.getBoolean("status");
                
                 o = new Order(orderid, userid, length, width, height, status);
            }
        } catch ( ClassNotFoundException | SQLException ex ) {
            throw new LegoHouseException("The world is falling, please contact IT-SUPPORT!", "index");
        }
        return o;
    }
    
    /**
     * returns list of all orders for employee to send orders.
     * @return returns list of all orders for employee to send orders.
     * @throws LegoHouseException throws Exception if there is no connection to DB. 
     */
     
    public static List<Order> allOrders() throws LegoHouseException {
       List<Order> temp = new ArrayList();
         try {
            Connection con = Connector.connection();
            String SQL = "SELECT * from orders";
            PreparedStatement ps = con.prepareStatement( SQL );
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                int orderid = rs.getInt("orderID");
                int userid = rs.getInt("userID");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                boolean status = rs.getBoolean("status");
                Order o = new Order(orderid, userid, length, width, height, status);
                temp.add(o);
            }
        } catch ( ClassNotFoundException | SQLException ex ) {
            throw new LegoHouseException("The world is falling, please contact IT-SUPPORT!", "index");
        }
        return temp;
    }
    
    /**
     * Makes it possible for employee to send order.
     * @param orderID not null.
     * @throws LegoHouseException throws Exception if there is no connection to DB. 
     */
    
     public static void sendOrder(int orderID) throws LegoHouseException{
        try {
            Connection con = Connector.connection();
            String SQL = "update orders set status = 1 where orderID = ?";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setInt(1, orderID);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
           throw new LegoHouseException("The world is falling, please contact IT-SUPPORT!", "index");
        }
    }
    
    
    
}
//    public static int brickID(int bricksize) throws OrderException, SQLException {
//        int temp = 0;
//        try {
//            Connection con = Connector.connection();
//            String SQL = "SELECT brickID from brick where bricksize = (?)";
//            PreparedStatement ps = con.prepareStatement( SQL );
//            ps.setInt( 1, bricksize );
//            ResultSet rs = ps.executeQuery();
//            if ( rs.next() ) {
//                int brickID = rs.getInt("brickID");
//                temp = brickID;
//            }
//        } catch ( ClassNotFoundException | SQLException ex ) {
//            throw new OrderException(ex.getMessage());
//        }
//        return temp;
//    }
    
//    public static void insertOrderItem(int orderID, int brickID, int quantity) throws OrderException{
//        try{
//        Connection con = Connector.connection();
//        String SQL = "INSERT INTO orderitems (orderID, brickID, quantity) VALUES (?, ?, ?)";
//            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, orderID);
//            ps.setInt(2, brickID);
//            ps.setInt(3, quantity);
//            ps.executeUpdate();
//        }catch(ClassNotFoundException | SQLException ex){
//          throw new OrderException(ex.getMessage());
//
//        }
//    }
//    
//    public static Brick getBrick(int brickID) throws OrderException, SQLException {
//        Brick b = null;
//        try {
//            Connection con = Connector.connection();
//            String SQL = "SELECT * from brick where brickID = (?)";
//            PreparedStatement ps = con.prepareStatement( SQL );
//            ps.setInt( 1, brickID );
//            ResultSet rs = ps.executeQuery();
//            if ( rs.next() ) {
//                int brickSize = rs.getInt("bricksize");
//                b = new Brick(brickID, brickSize, 0);
//            }
//        } catch ( ClassNotFoundException | SQLException ex ) {
//            throw new OrderException(ex.getMessage());
//        }
//        return b;
//    }
   


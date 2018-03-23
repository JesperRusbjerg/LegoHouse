package FunctionLayer;


public class Order {

private int orderID;
private int userID;
private int length;
private int width;
private int height;
private boolean sent;

    public Order(int orderID, int userID, int length, int width, int height, boolean sent) {
        this.orderID = orderID;
        this.userID = userID;
        this.length = length;
        this.width = width;
        this.height = height;
        this.sent = sent;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getUserID() {
        return userID;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isSent() {
        return sent;
    }

    

   



    
}

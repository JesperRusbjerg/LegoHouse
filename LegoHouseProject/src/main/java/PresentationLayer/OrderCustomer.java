package PresentationLayer;


import FunctionLayer.House;
import FunctionLayer.LogicFacade;
import FunctionLayer.LegoHouseException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderCustomer extends Command {

    public OrderCustomer() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LegoHouseException {

        if(request.getParameter("logout") != null){
                request.getSession().removeAttribute("user");
                return "index";
                
            }
        
        // If you hit the calc button, this wont be null.
        if (request.getParameter("placeOrder") == null && request.getParameter("showOrders") == null && request.getParameter("orderDetails") == null) {
            if(request.getSession().getAttribute("length") != null && request.getSession().getAttribute("width") != null && request.getSession().getAttribute("height") != null){
                request.getSession().removeAttribute("length");
                request.getSession().removeAttribute("width");
                request.getSession().removeAttribute("height");
            }
            
            int length = Integer.parseInt(request.getParameter("length"));
            int width = Integer.parseInt(request.getParameter("width"));
            int height = Integer.parseInt(request.getParameter("height"));
            request.getSession().setAttribute("length", length);
            request.getSession().setAttribute("width", width);
            request.getSession().setAttribute("height", height);
            
            if (length < 10 || length > 100 || width < 8 || width >= 50 || height < 4 || height >=50){
               throw new LegoHouseException("Wrong input!", "customerpage");
            }
            
                    House h = LogicFacade.Calculator(length, width, height);
                    request.setAttribute("calculate", h);
        }
        
        // If youve hit the place order button this works!
        if (request.getParameter("placeOrder") != null) {
          
                User u = (User) request.getSession().getAttribute("user");
                int length = (int) request.getSession().getAttribute("length");
                int width = (int) request.getSession().getAttribute("width");
                int height = (int) request.getSession().getAttribute("height");
                request.getSession().removeAttribute("length");
                request.getSession().removeAttribute("width");
                request.getSession().removeAttribute("height");
                
                Order o = LogicFacade.createOrder(u.getId(), length, width, height);
                request.setAttribute("order", o);
  
        }

        //show orders
        
        if (request.getParameter("showOrders") != null || request.getParameter("orderDetails") != null) {
            
            User u = (User) request.getSession().getAttribute("user");
                List<Order> orderList = LogicFacade.userOrders(u.getId());
                request.setAttribute("orderList", orderList);

            
            
        }
            if(request.getParameter("orderDetails") != null){
               
                int orderID = Integer.parseInt(request.getParameter("orderid"));
                
               
                    Order orderDetails = LogicFacade.orderDetail(orderID);
                    int length = orderDetails.getLength();
                    int width = orderDetails.getWidth();
                    int height = orderDetails.getHeight();
                    House h = LogicFacade.Calculator(length, width, height);
                    
                    request.setAttribute("length", length);
                    request.setAttribute("width", width);
                    request.setAttribute("height", height);
                    request.setAttribute("finalOrderDetails", h);
            }
            
            

            return "customerpage";
    }

}


package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LegoHouseException;
import FunctionLayer.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jesper
 */
public class OrderEmployee extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LegoHouseException {
 
        if(request.getParameter("logout") != null){
                request.getSession().removeAttribute("user");
                return "index";
                
            }
        
        if(request.getParameter("sendOrder") != null){
        
        int orderid = Integer.parseInt(request.getParameter("sendOrder"));
          LogicFacade.sendOrder(orderid);
        
        if(request.getParameter("sendOrder") != null){
          List<Order> o = LogicFacade.allOrders();
           request.setAttribute("orderList", o);
            
          
        }
        }

  if(request.getParameter("showOrdersEmp") != null) {
           List<Order> o = LogicFacade.allOrders();
           request.setAttribute("orderList", o);
            }
       
        return "employeepage";
    
    }
}
    
    
    
    


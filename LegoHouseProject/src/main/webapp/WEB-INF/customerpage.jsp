

<%@page import="FunctionLayer.House"%>
<%@page import="FunctionLayer.Order"%>
<%@page import="java.util.List"%>
<%@page import="FunctionLayer.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Customer home page</title>
    </head>
    <body>


        <div class="container-fluid">
            <div class="row">
                <%if (request.getAttribute("orderList") == null) { %>

                <div class="col-md-6">
                    <% User user = (User) request.getSession().getAttribute("user"); %>
                    <h1>Hello <% out.print(user.getEmail());%> </h1>
                    You are now logged in as a customer of our wonderful site.

                    <p> this is where you can place your order! :D </p>

                    <% if (request.getAttribute("error") != null) {
                    %>
                    <h2> <% out.print(request.getAttribute("error")); %> </h2>
                    <% }%>
                    <form action="FrontController" method="post">
                        <div class="form-group">
                            <input type="hidden" name="command" value="orderCustomer">
                            <label for="length">Length of lego buidling:</label>
                            <input type="number" name="length" class="form-control" value="10">
                            <br>
                            <label for="width">Width of lego buidling:</label>
                            <input type="number" name="width" class="form-control" value="8">
                            <br>
                            <label for="height">Height of lego buidling:</label>
                            <input type="number" name="height" class="form-control" value="4">
                            <br>
                            <input type="submit" class="btn btn-primary" value="Calculate Bricks!"/><br>
                        </div>
                    </form>

                    <br>

                    <form action="FrontController" method="post">
                        <div class="form-group">
                            <input type="hidden" name="command" value="orderCustomer">
                            <input type="hidden" name="showOrders">
                            <input type="submit" class="btn btn-primary" value="Show Orders!"/><br>
                        </div>
                    </form>
                    
                            <%@include file="Include/LogOut.jsp" %>


                </div>
                <% if (request.getAttribute("calculate") != null) {
                        House h = (House) request.getAttribute("calculate");
                %>
                <div class="col-md-6">
                    <h1> Bricks needed to build the house </h1>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Bricks needed to build house </th>
                                <th> 2x4 Bricks </th>
                                <th> 2x2 Bricks </th>
                                <th>1x2 Bricks </th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th> </th>
                                <th> <% out.print(h.getX4Brick()); %> </th>
                                <th> <% out.print(h.getX2Brick()); %> </th> 
                                   <th> <% out.print(h.getX1Brick()); %> </th> 

                            </tr>
                        </tbody>
                    </table>

                    <form action="FrontController" method="post">
                        <div class="form-group">
                            <input type="hidden" name="command" value="orderCustomer">
                            <input type="hidden" name="placeOrder">
                            <input type="submit" class="btn btn-primary" value="Place Order!"/><br>
                        </div>
                    </form>
                </div>
                <% }
                    if (request.getAttribute("order") != null) {
                        Order o = (Order) request.getAttribute("order");
                %>
                <div class="col-md-6">
                    <h1> Order Created! </h1>  
                    <p> Order ID: <% out.print(o.getOrderID());%> </p>
                    <p> Status for order: Not Shipped Yet </p>
                    <p>Your orders including their details can be seen under "Show Orders" </p>
                    <p> You can also see when your order has been shipped under "Show Orders" </p>

                </div>
                <%}%>
                
                
                


                <%} else {
                    List<Order> orders = (List) request.getAttribute("orderList");
                %>
                <div class="col-md-6">

                    <h1> Order: </h1>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>OrderID </th>
                                <th> UserID </th>
                                <th> Shipping Status </th>
                            </tr>
                        </thead>
                        <%  for (Order elem : orders) { %>
                        <tbody>
                            <tr>
                                <th> <% out.print(elem.getOrderID()); %> </th>
                                <th> <% out.print(elem.getUserID()); %> </th> 
                                    <% boolean sent = elem.isSent();
                                    if (sent) {%>
                                <th> Order has been shipped!  </th>
                                    <%} else {%>
                                <th> Order NOT SENT  </th>
                                    <%} %>
                                <th>

                                    <form action="FrontController" method="post">
                                        <div class="form-group">
                                            <input type="hidden" name="command" value="orderCustomer">
                                            <input type="hidden" name="orderDetails">
                                            <input type="hidden" name="orderid" value="<% out.print(elem.getOrderID()); %>">
                                            <input type="submit" class="btn btn-primary" value="Order Details!"/><br>
                                        </div>
                                    </form>
                                </th>
                                <% }%>
                            </tr>
                        </tbody>
                    </table>

                  <%@include file="Include/LogOut.jsp" %>           
                            
                </div>
                <%if (request.getAttribute("finalOrderDetails") != null) {
                        House h = (House) request.getAttribute("finalOrderDetails");
                        
                        int length = (int) request.getAttribute("length");
                        int width = (int) request.getAttribute("height");
                        int height = (int) request.getAttribute("height");
                %>
                <div class="col-md-6">

                    <h1> Order Details: </h1>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                
                                <th> Bricks </th>
                                 <th> Quantity </th>
                                <th>Height</th>
                                <th>Width</th>
                                <th>Height</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>2x4  </th>
                                <th> <% out.print(h.getX4Brick()); %> </th>
                                <th> <% out.print(length); %> </th>
                                <th> <% out.print(width); %> </th>
                                <th> <% out.print(height); %> </th>
                            </tr> <tr>
                                <th>2x2 </th>
                                <th>  <% out.print(h.getX2Brick()); %> </th>
                                </tr>  <tr>
                                    <th> 1x2 </th>
                                <th> <% out.print(h.getX1Brick()); %> </th>
                            </tr> 
                        </tbody>
                    </table>

                </div>
                <% }%>
                <%}%>
                
                
            </div>
        </div>

            
            
            
    </body>
</html>

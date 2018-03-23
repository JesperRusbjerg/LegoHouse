<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.Order"%>
<%@page import="FunctionLayer.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Employee home page</title>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">

                <div class="col-md-12">
                    <% User u = (User) request.getSession().getAttribute("user"); %>


                    <%if (request.getAttribute("orderList") == null) { %>
                    <h1>hej <% out.print(u.getEmail()); %> </h1>
                    <p> Du er da vidst månedens medarbejder, se nu at få sendt nogle ordre afsted! under "Show Orders" ;) </p>
                    <form action="FrontController" method="post">
                        <div class="form-group">
                            <input type="hidden" name="command" value="orderEmployee">
                            <input type="hidden" name="showOrdersEmp">
                            <input type="submit" class="btn btn-primary" value="Show Orders!"/><br>
                        </div>
                    </form>
                    <%@include file="Include/LogOut.jsp" %> 

                    <% } else {%>
                    <h1> All Orders: </h1>

                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th> OrderID</th>
                                <th> UserID</th>
                                <th> Length</th>
                                <th> Width</th>
                                <th> Height </th>
                                <th> Status</th>
                            </tr>
                        </thead>

                        <% List<Order> o = (List<Order>) request.getAttribute("orderList");
                            for (Order elem : o) {%>

                        <tbody>
                            <tr>
                                <th> <% out.print(elem.getOrderID());  %> </th>        
                                <th> <% out.print(elem.getUserID());  %> </th>  
                                <th> <% out.print(elem.getLength());  %> </th> 
                                <th> <% out.print(elem.getWidth());  %> </th> 
                                <th> <% out.print(elem.getHeight());  %> </th> 
                                    <% boolean sent = elem.isSent();
                                        String status;
                                        if (sent) {
                                            status = "Order sent!";
                                        } else {
                                            status = "Order NOT sent!";
                                        }
                                    %>
                                <th> <% out.print(status);  %> </th> 
                                    <%if (!elem.isSent()) { %>
                                <th> 
                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="orderEmployee">
                                        <input type="hidden" name="sendOrder" value="<%out.print(elem.getOrderID());%>">
                                        <input type="submit" class="btn btn-primary" value="Send Order!">
                                    </form>
                                </th>
                                <%}%>
                            </tr>

                            <%}%>
                        </tbody>
                    </table>
                    <%@include file="Include/LogOut.jsp" %> 
                    <%}%>
                </div>
            </div>
        </div>

    </body>
</html>

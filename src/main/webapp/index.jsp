<%-- 
    Document   : index
    Created on : Aug 22, 2017, 2:01:06 PM
    Author     : kasper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Welcome page</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
            <div class="col-md-6">
               <h1>Lego-Housing! </h1>
            <h5> Login or Register to proceed! </h5>
            <br>
            <br>
            </div>
           </div>
           
            <div class="row">
        
                <div class="col-md-6">
                
        
        
        
         <h3> Login: </h3>
                    <form name="login" action="FrontController" method="POST">
                        <div class="form-group">
                        <input class="form-control" type="hidden" name="command" value="login">
                       <label for="email">Email:</label>
                        <input class="form-control" type="text" name="email" placeholder="someone@nowhere.com">
                        <br>
                        <label for="password">Password:</label>
                        <input class="form-control" type="password" name="password" value="sesam">
                        <br>
                        <input class="btn-primary" type="submit" value="Submit">
                        </div>
                    </form>
                </div>                
                
                <div class="col-md-6">
                    <h3> Register: </h3>
                    <form name="register" action="FrontController" method="POST">
                        <div class="form-group">
                        <input type="hidden" name="command" value="register">
                       <label for="email">Email:</label>
                        <input class="form-control" type="text" name="email" value="someone@nowhere.com">
                        <br>
                         <label for="password1">Password:</label>
                        <input class="form-control" type="password" name="password1" value="sesam">
                        <br>
                        <label for="password2">Retype Password:</label>
                        <input class="form-control" type="password" name="password2" value="sesam">
                        <br>
                        <input class="btn-primary" type="submit" value="Submit">
                        </div>
                    </form>
                </div>
        <% String error = (String) request.getAttribute( "error");
           if ( error != null) { %>
           <h2>Error!!</h2>
           <p><%= error %> </p>
        <% }
        %>
        
            </div>
        </div>
    </body>
</html>

package PresentationLayer;

import FunctionLayer.LegoHouseException;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put( "login", new Login() );
        commands.put( "register", new Register() );
        commands.put("orderCustomer", new OrderCustomer() );
        commands.put("orderEmployee", new OrderEmployee() );
    }

    abstract String execute( HttpServletRequest request, HttpServletResponse response ) 
            throws LegoHouseException;
    
    static Command from( HttpServletRequest request ) {
        String commandName = request.getParameter( "command" );
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand() );
    }

}

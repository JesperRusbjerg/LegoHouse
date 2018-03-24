package PresentationLayer;

import FunctionLayer.LegoHouseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 The purpose of UnknownCommand is to...

 @author jesper
 */
public class UnknownCommand extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LegoHouseException {
        String msg = "Unknown command. Contact IT";
        throw new LegoHouseException( msg , "index");
    }

}

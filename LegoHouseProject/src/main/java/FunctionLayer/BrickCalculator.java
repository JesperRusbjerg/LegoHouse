
package FunctionLayer;

import DBAccess.StorageFacade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BrickCalculator {
    
    public House Calculator(int userLength, int userWidth, int userHeight){
        
     int totalFours = 0;
        int totalTwos = 0;
        int totalOnes = 0;
       boolean everySecond = true;
       int row = userLength;
      
        
         for (int i = 1; i <= userHeight*2; i++) {
            
                    int surfacearea1 = row;
                    int surfacearea2 = row-4;
                    
                 if(everySecond){
                     totalFours += (surfacearea1/4)*2;
                     surfacearea1= surfacearea1%4;
                     totalTwos += (surfacearea1/2)*2;
                     surfacearea1=surfacearea1%2;
                     totalOnes += (surfacearea1/1)*2;
                     
                     everySecond = false;
                 }else{
                      totalFours += (surfacearea2/4)*2;
                     surfacearea2= surfacearea2%4;
                     totalTwos += (surfacearea2/2)*2;
                     surfacearea2=surfacearea2%2;
                     totalOnes += (surfacearea2/1)*2;
                     
                     everySecond = true;
                 }
                 
                 if(i == userHeight){
                 row = userWidth;
                 everySecond = false;
             }
         }
         
         if(userLength%4 == 0){
             totalFours -= 4;
             totalTwos += 4;
             
         }else{
             totalFours -= 1;
             totalTwos -= 2;
         }
        
        House h = new House(totalFours, totalTwos, totalOnes);
        
    return h;
    }
    
}

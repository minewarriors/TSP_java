
package SerialController;

import java.util.ArrayList;

/**
 *
 * @author Christiaan
 */
public class DataLogger {
   private static ArrayList<String> data = new ArrayList<>();

    //public DataLogger() {
  //      data.add("Welcome to the control pannel.");
   // }
    
    

    public static ArrayList<String> getData() {
        return data;
    }

    public static void addData(String text) {
        DataLogger.data.add(text);
    }
    
    
}

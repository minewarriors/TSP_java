
package SerialController;

import java.util.ArrayList;

/**
 *
 * @author Christiaan
 */
public class DataLogger {
   private static ArrayList<String> data = new ArrayList<>();  //data logger om alle informatie ik te printen zowel van het controle paneel als van de algorithmes 

   
    public static ArrayList<String> getData() {
        return data;
    }

    public static void addData(String text) {
        DataLogger.data.add(text);
    }
    
    
}

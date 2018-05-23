
package SerialController;

import java.util.ArrayList;

/**
 *
 * @author Christiaan
 */
public class DataLogger {
    ArrayList<String> data = new ArrayList<>();

    public DataLogger() {
        data.add("Welcome to the control pannel.");
    }
    
    

    public ArrayList<String> getData() {
        return data;
    }

    public void addData(String text) {
        this.data.add(text);
    }
    
    
}

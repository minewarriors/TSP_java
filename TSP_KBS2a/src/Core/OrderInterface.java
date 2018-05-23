
package Core;

import BPP.*;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Christiaan
 */
public interface OrderInterface {

    void print();
    
    void addToOrder(Product p);
    
    ArrayList<Product> getOrderPackages();
    
    String toString();
}

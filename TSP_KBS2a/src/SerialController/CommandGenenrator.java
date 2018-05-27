package SerialController;

import Core.Box;
import Core.Product;
import java.util.ArrayList;

/**
 *
 * @author Christiaan
 */

public abstract class CommandGenenrator {

    public static ArrayList<String> generateCommandArray(ArrayList<Product> tspOutput, Box A, Box B, Box C) {

        ArrayList<String> CommandArray = new ArrayList<>();
        String ProductFoundIn = null;
        String output;
        
        CommandArray.add("3-3-2\r\n"); //het commando om de robot in het midden te laten beginnen [3.3]

        for (Product x : tspOutput) {

            for (Product product : A.getProductBoxArray()) {
                if (product == x) {
                    ProductFoundIn = "1";
                }
            }

            for (Product product : B.getProductBoxArray()) {
                if (product == x) {
                    ProductFoundIn = "2";
                }
            }

            for (Product product : C.getProductBoxArray()) {
                if (product == x) {
                    ProductFoundIn = "3";
                }
            }
            if (ProductFoundIn != null) {
                output = (x.getX() + "-" + x.getY() + "-" + ProductFoundIn + "\r\n"); //het order zoek en grijp cammando
                CommandArray.add(output);
            }
        }
        CommandArray.add("1-1-1-9\r\n"); //het stop cammando
        return CommandArray;
    }

}

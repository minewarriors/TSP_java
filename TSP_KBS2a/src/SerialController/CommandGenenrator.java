package SerialController;

import Core.Box;
import Core.Product;
import java.util.ArrayList;

public abstract class CommandGenenrator {

    public static ArrayList<String> generateCommandArray(ArrayList<Product> tspOutput, Box A, Box B, Box C) {

        ArrayList<String> CommandArray = new ArrayList<>();
        String ProductFoundIn = null;
        String output;

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
                output = (x.getX() + "-" + x.getY() + "-" + ProductFoundIn + "\r\n");
                CommandArray.add(output);
            }
        }
        return CommandArray;
    }

}

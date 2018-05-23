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
                    ProductFoundIn = "A";
                }
            }

            for (Product product : B.getProductBoxArray()) {
                if (product == x) {
                    ProductFoundIn = "B";
                }
            }

            for (Product product : C.getProductBoxArray()) {
                if (product == x) {
                    ProductFoundIn = "C";
                }
            }
            if (ProductFoundIn != null) {
                output = (x.getX() + "-" + x.getY() + "-" + ProductFoundIn);
                CommandArray.add(output);
            }
        }
        return CommandArray;
    }
}

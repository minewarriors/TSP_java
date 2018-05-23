package BPP;

import Core.RandomOrder;
import Core.Box;
import static Core.BPPInterface.boxSize;
import static BPPAlgorithms.Algorithms.BestFitDecreasing;
import static BPPAlgorithms.Algorithms.OwnMethod;
import static BPPAlgorithms.Algorithms.firstFit;

public class Main {

    public static void main(String[] args) {

        BPPMainScreen headscreen = new BPPMainScreen();
        RandomOrder b2 = new RandomOrder();
        b2.print();

        Box d = new Box(boxSize);
        Box e = new Box(boxSize);
        Box f = new Box(boxSize);

        if (OwnMethod(b2, d, e, f)) {
            System.out.println("---- Succes ----");
        } else {
            System.out.println("---- Te weinig ruimte ----");
        }

        //A.clearBox();
        System.out.println("box A");

        d.getProductBoxArray().forEach((a) -> {
            System.out.println(a);
        });

        System.out.println("box B");

        e.getProductBoxArray().forEach((a) -> {
            System.out.println(a);
        });

        System.out.println("box C");

        f.getProductBoxArray().forEach((a) -> {
            System.out.println(a);
        });

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import Window.BPPMainScreen;
import Core.Product;
import Window.MainWindow;
import Window.TSPWindow;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

/**
 *
 * @author jelle
 */
public class Driver {

    public final static boolean VERBOSE_FLAG = true;
    private ArrayList<Product> intialRoute;

    public Driver() {
        intialRoute = new ArrayList<Product>();
    }

    public ArrayList<Product> getIntialRoute() {
        return intialRoute;
    }

    public void addToIntialRoute(Product product) {
        intialRoute.add(product);
    }

    public void clearIntialRoute() {
        intialRoute.clear();
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        TSPWindow TSP = new TSPWindow(driver);
        BPPMainScreen BPP = new BPPMainScreen();
        MainWindow Menu = new MainWindow(TSP, BPP);
    }

    //print hoelang het process heeft geduurt
    public void printDuration(Instant startInstant) {
        Duration permutationDuration = Duration.between(startInstant, Instant.now());
        //tijd in min en sec
        long minutes = permutationDuration.toMinutes();
        long seconds = permutationDuration.getSeconds();

        if (seconds > 59) {
            long tempSeconds = seconds - 60 * minutes;
            long tempMilliSeconds = permutationDuration.toMillis() - seconds * 1000;
            System.out.println("Duration: " + minutes + " minutes " + tempSeconds + " seconds " + tempMilliSeconds + " milliseconds" + "(" + permutationDuration + ")");
        } else if (seconds > 0) {
            long milliSeconds = permutationDuration.toMillis() - seconds * 1000;
            System.out.println("Duration: " + seconds + " seconds " + milliSeconds + " milliseconds" + "(" + permutationDuration + ")");
        } else {
            System.out.println("Duration: " + permutationDuration.toMillis() + "(" + permutationDuration + ")");
        }
    }

    //print de kortste routes
    public void printResults(BruteForce bruteForce, ArrayList<Route> shortestRoute) {
        if (VERBOSE_FLAG) {
            System.out.println("");
        }
        printHeading("Shortest Route", "Distance");
        shortestRoute.stream().forEach(x -> System.out.println(x + " | " + bruteForce.getTotalDistance(x)));
    }

    public void printResults(WillekeurigBeperkt wlbp, ArrayList<Route> shortestRoute) {
        if (VERBOSE_FLAG) {
            System.out.println("");
        }
        printHeading("Shortest Route", "Distance");
        shortestRoute.stream().forEach(x -> System.out.println(x + " | " + wlbp.getTotalDistance(x)));
    }

    //maakt de kolommen netjes als permutatie van aantal producten veranderd
    public void printHeading(String headingcolumn1, String remainingHeadingColumns) {
        int productNamesLength = 0;
//        for (int x = 0; x < intialRoute.size(); x++) {
//            productNamesLength += intialRoute.get().getProductId().length();
//        }
        int arrayLength = productNamesLength + intialRoute.size() * 2;
        int partialLength = (arrayLength - headingcolumn1.length()) / 2;
        for (int x = 0; x < partialLength; x++) {
            System.out.println(" ");
        }
        System.out.println(headingcolumn1);
        for (int x = 0; x < partialLength; x++) {
            System.out.println(" ");
        }
        if ((arrayLength % 2) == 0) {
            System.out.println(" ");
        }
        System.out.println(" | " + remainingHeadingColumns);
        productNamesLength += remainingHeadingColumns.length() + 3;
//        for (int x = 0; x < productNamesLength + intialRoute.size() * 2; x++) {
//            System.out.println("-");
//        }
        System.out.println("");
    }

    public void printShortestRoute(Route shortestroute) {
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Shortest route found so far: " + shortestroute);
        System.out.println("Total distance " + shortestroute.calculateTotalDistance());
        System.out.println("------------------------------------------------------------------------------------------");
    }
}

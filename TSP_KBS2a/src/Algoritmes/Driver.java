/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import Core.Product;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jelle
 */
public class Driver {

    final static boolean VERBOSE_FLAG = false;

    private static ArrayList<Product> intialRoute = new ArrayList<Product>(Arrays.asList(
            new Product(1, 1, 2, "Red", 1),
            new Product(2, 5, 5, "Red", 1),
            new Product(3, 3, 2, "Red", 1),
            new Product(4, 4, 4, "Red", 1),
            new Product(5, 1, 3, "Red", 1),
            new Product(6, 3, 5, "Red", 1),
            new Product(7, 1, 2, "Red", 1),
            new Product(8, 5, 5, "Red", 1)
    ));

    public static void main(String[] args) {
        Driver driver = new Driver();
        Instant startInstant = Instant.now();
        BruteForce bruteforce = new BruteForce();
        Route currentRoute = new Route(intialRoute);

        if (VERBOSE_FLAG) {
            driver.printHeading("Route", "Distance | Shortest Distance | Permutation #");
        } else {
            System.out.println("Permutation in progress ...");
        }

        driver.printResults(bruteforce, bruteforce.permutateProducten(0, currentRoute, new Route(currentRoute)));

        driver.printDuration(startInstant);
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
}

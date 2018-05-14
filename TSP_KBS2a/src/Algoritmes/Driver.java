/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import Core.Product;
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
            new Product(6, 3, 5, "Red", 1)
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

    public void printDuration(Instant startInstant) {

    }

    public void printResults(BruteForce bruteForce, ArrayList<Route> shortestRoute) {

    }

    public void printHeading(String headingcolumn1, String remainingHeadingColumns) {

    }
}

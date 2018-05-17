/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import Core.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author jelle
 */
public class BruteForce {

    static int permutationNumber = 1;
    ArrayList<Route> shortestRoutes = new ArrayList<Route>();

    public ArrayList<Route> permutateProducten(int x, Route currentRoute, Route shortestRoute) {
        currentRoute.getProducts().stream().filter(y -> currentRoute.getProducts().indexOf(y) >= x).forEach(y -> {
            int indexofY = currentRoute.getProducts().indexOf(y);
            Collections.swap(currentRoute.getProducts(), indexofY, x);
            permutateProducten(x + 1, currentRoute, shortestRoute);
            Collections.swap(currentRoute.getProducts(), x, indexofY);
        });
        if (x == currentRoute.getProducts().size() - 1) {

            //print huidige route en lengte
            if (Driver.VERBOSE_FLAG) {
                System.out.println(currentRoute + " |   " + getTotalDistance(currentRoute) + "   |   " + getTotalDistance(shortestRoute) + permutationNumber++);
            }

            //maakt lijst van nieuwste korste wegen
            if ((int) calculateTotalDistance(currentRoute) <= (int) calculateTotalDistance(shortestRoute)) {
                shortestRoute.getProducts().clear();
                shortestRoute.getProducts().addAll(currentRoute.getProducts());
                addShortestRoute(new Route(shortestRoute));
            }
            //print hele route
            if (Driver.VERBOSE_FLAG) {
                System.out.println("    |   " + getTotalDistance(shortestRoute));
            }
        }
        return shortestRoutes;
    }

    public int calculateTotalDistance(Route route) {
        int productsSize = route.getProducts().size();

        //bepalen afstand tussen plaatsen
        return (int) (route.getProducts().stream().mapToDouble(x -> {
            int productIndex = route.getProducts().indexOf(x);
            double returnValue = 0;
            if (productIndex < productsSize - 1) {
                returnValue = x.measureDistance(route.getProducts().get(productIndex + 1));
            }
            return returnValue;
            // laatste stad toevoegen aan de lengte
        }).sum() + route.getProducts().get(0).measureDistance(route.getProducts().get(productsSize - 1)));
    }

    // code to align the columns
    public String getTotalDistance(Route route) {
        String returnValue = Integer.toString(calculateTotalDistance(route));
        if (returnValue.length() == 4) {
            returnValue = " " + returnValue;
        } else if (returnValue.length() == 3) {
            returnValue = " " + returnValue;
        }
        return returnValue;
    }

    //voegt de korste route toe en verwijderd en langere route
    public void addShortestRoute(Route route) {
        shortestRoutes.removeIf(x -> (int) calculateTotalDistance(x) > (int) calculateTotalDistance(route));
        shortestRoutes.add(route);
    }

}

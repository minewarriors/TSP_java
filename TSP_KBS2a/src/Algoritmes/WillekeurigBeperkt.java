/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import Core.Product;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author jelle
 */
public class WillekeurigBeperkt {
    static int permutationNumber = 1;
    private ArrayList<Route> shortestRoutes = new ArrayList<>();

    public ArrayList<Route> permutateProducts(int permutationMaximum, Route currentRoute, Route shortestRoute) {
        for (int temp = 0; temp < permutationMaximum; temp++) {
            ArrayList<Product> tempProductArray = currentRoute.getProducts();
            Collections.shuffle(tempProductArray);
            Route r = new Route(tempProductArray);
            
            if (Driver.VERBOSE_FLAG) {
                System.out.println(currentRoute + " |   " + getTotalDistance(currentRoute) + "   |   " + getTotalDistance(shortestRoute) + permutationNumber++);
            }
            
            if ((int) calculateTotalDistance(r) <= (int) calculateTotalDistance(shortestRoute)) {
                shortestRoute.getProducts().clear();
                shortestRoute.getProducts().addAll(currentRoute.getProducts());
                addShortestRoute(new Route(shortestRoute));
            }
        }
        return shortestRoutes;
    }

    public int calculateDistance(Route route) {
        int arraySize = route.getProducts().size();
        ArrayList<Product> products = route.getProducts();

        //bepalen afstand tussen plaatsen
        return (int) (products.stream().mapToDouble(x -> {
            int productIndex = products.indexOf(x);
            double returnValue = 0;
            if (productIndex < arraySize - 1) {
                returnValue = x.measureDistance(products.get(productIndex + 1));
            }
            return returnValue;
            // laatste stad toevoegen aan de lengte
        }).sum() + route.getProducts().get(0).measureDistance(route.getProducts().get(arraySize - 1)));
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

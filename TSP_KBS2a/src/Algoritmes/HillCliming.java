/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import Core.Product;

/**
 *
 * @author jelle
 */
public class HillCliming {

    public static final int maxima = 100;

    public Route findShortestRoute(Route currentRoute) {
        Route adjecentRoute;
        int counter = 0;
        String compareRoutes = null;
        while (counter<maxima) {
            adjecentRoute = obtainAdjecentRoute(new Route(currentRoute));
            if (adjecentRoute.calculateTotalDistance() <= currentRoute.calculateTotalDistance()) {
                compareRoutes = "<= (proceed";
                counter = 0;
                currentRoute = new Route(adjecentRoute);
            } else {
                compareRoutes = "> (stay) iteration # "+counter;
                System.out.println(" |      ");
            }
            if (counter == maxima) {
                System.out.println("        | potential maximum");
                System.out.println("        | " + compareRoutes);
            }
            counter++;
        }
        return null;
    }

    public Route obtainAdjecentRoute(Route route) {
        int x1 = 0;
        int x2 = 0;
        while (x1 == x2) {
            x1 = (int) (route.getProducts().size() * Math.random());
            x2 = (int) (route.getProducts().size() * Math.random());
        }
        Product p1 = route.getProducts().get(x1);
        Product p2 = route.getProducts().get(x2);
        route.getProducts().set(x1, p2);
        route.getProducts().set(x2, p1);
        
        return route;
    }

}

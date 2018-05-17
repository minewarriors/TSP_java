/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import Core.Product;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jelle
 */
public class EigenMethode {

    //Neemt oorspronkelijke hoeveelheid producten op in ArrayList
    public Route FindShortestRoute(ArrayList<Product> producten) {
        ArrayList<Product> shortestRouteProducts = new ArrayList<Product>(producten.size());

        //Print de oorsprongelijke route van de pakketjes met oorspronkelijke afstand
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Initial Route           ==>" + Arrays.toString(producten.toArray()));
        System.out.println("Total distance          ==> " + new Route(producten).calculateTotalDistance());

        // Returned nieuwste kortste route doormiddel van nearestneighbor
        return new Route(shortestRouteProducts);
    }
}
